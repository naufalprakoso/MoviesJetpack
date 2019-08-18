package com.naufalprakoso.moviesjetpack.data.source

import com.naufalprakoso.moviesjetpack.data.source.remote.ApiResponse
import androidx.lifecycle.LiveData
import com.naufalprakoso.moviesjetpack.utils.AppExecutors
import androidx.lifecycle.MediatorLiveData
import com.naufalprakoso.moviesjetpack.vo.Resource
import com.naufalprakoso.moviesjetpack.data.source.remote.StatusResponse

abstract class NetworkBoundResource<ResultType, RequestType>(
    private val mExecutors: AppExecutors? = null
) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>?

    protected abstract fun saveCallResult(data: RequestType)

    init {
        result.value = Resource.loading(null)

        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData -> result.setValue(Resource.success(newData)) }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(
            dbSource
        ) { newData -> result.setValue(Resource.loading(newData)) }
        apiResponse?.let {
            result.addSource(it) { response ->
                result.removeSource(it)
                result.removeSource(dbSource)

                when (response.status) {
                    StatusResponse.SUCCESS -> mExecutors?.diskIO()?.execute {

                        saveCallResult(response.body)

                        mExecutors.mainThread().execute {
                            result.addSource(
                                loadFromDB()
                            ) { newData -> result.setValue(Resource.success(newData)) }
                        }

                    }
                    StatusResponse.EMPTY -> mExecutors?.mainThread()?.execute {
                        result.addSource(
                            loadFromDB()
                        ) { newData -> result.setValue(Resource.success(newData)) }
                    }
                    StatusResponse.ERROR -> {
                        onFetchFailed()
                        result.addSource(dbSource) { newData ->
                            result.setValue(response.message?.let { Resource.error(it, newData) })
                        }
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }
}
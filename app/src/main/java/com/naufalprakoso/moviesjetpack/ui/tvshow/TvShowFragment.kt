package com.naufalprakoso.moviesjetpack.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.ui.detail.DetailMovieActivity
import com.naufalprakoso.moviesjetpack.utils.Const
import com.naufalprakoso.moviesjetpack.viewmodel.ViewModelFactory
import com.naufalprakoso.moviesjetpack.vo.Status
import kotlinx.android.synthetic.main.fragment_tv_show.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class TvShowFragment : Fragment() {

    private lateinit var adapter: TvShowPagedAdapter
    private var viewModel: TvShowViewModel? = null

    @Inject
    var factory: ViewModelProvider.Factory? = null

    companion object {
        fun newInstance(): Fragment {
            return TvShowFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            progress_bar.visibility = View.VISIBLE
            viewModel = obtainViewModel(activity)

            rv_tv_show.layoutManager = LinearLayoutManager(context)
            rv_tv_show.setHasFixedSize(true)

            viewModel?.getTvShowsPaged()?.removeObservers(this)
            viewModel?.setUsername("Naufal Prakoso")
            viewModel?.getTvShowsPaged()?.observe(this, Observer { it ->
                when (it.status) {
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE

                        adapter = TvShowPagedAdapter(it.data!!) {
                            val intent = Intent(context, DetailMovieActivity::class.java).apply {
                                putExtra(Const.MOVIE_ID, it.id)
                                putExtra(Const.MOVIE_TYPE, "tv_show")
                            }
                            context?.startActivity(intent)
                        }
                        adapter.notifyDataSetChanged()
                        adapter.submitList(it.data)
                        rv_tv_show.adapter = adapter
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): TvShowViewModel? {
        factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(TvShowViewModel::class.java) }
    }
}

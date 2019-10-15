package com.naufalprakoso.moviesjetpack.data.source.remote.response

import android.app.Application
import java.io.IOException
import org.json.JSONException
import org.json.JSONObject

class JsonHelper(
    private val application: Application
) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = application.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun getMovie(movieId: Int): MovieResponse? {
        var movieResponse: MovieResponse? = null

        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponse.json"))
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)
                val id = movie.getInt("id")

                if (id == movieId) {
                    val title = movie.getString("title")
                    val overview = movie.getString("overview")
                    val voteAverage = movie.getDouble("vote_average")
                    val posterPath = movie.getString("poster_path")
                    val releaseDate = movie.getString("release_date")
                    val voteCount = movie.getInt("vote_count")

                    movieResponse = MovieResponse(
                        id,
                        title,
                        overview,
                        voteAverage,
                        posterPath,
                        releaseDate,
                        voteCount
                    )
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return movieResponse
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponse.json"))
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val overview = movie.getString("overview")
                val voteAverage = movie.getDouble("vote_average")
                val posterPath = movie.getString("poster_path")
                val releaseDate = movie.getString("release_date")
                val voteCount = movie.getInt("vote_count")

                val movieResponse = MovieResponse(
                    id,
                    title,
                    overview,
                    voteAverage,
                    posterPath,
                    releaseDate,
                    voteCount
                )
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShows(): List<TvShowResponse> {
        val list = ArrayList<TvShowResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponse.json"))
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getInt("id")
                val title = tvShow.getString("title")
                val overview = tvShow.getString("overview")
                val voteAverage = tvShow.getDouble("vote_average")
                val posterPath = tvShow.getString("poster_path")
                val releaseDate = tvShow.getString("release_date")
                val voteCount = tvShow.getInt("vote_count")

                val tvShowResponse = TvShowResponse(
                    id,
                    title,
                    overview,
                    voteAverage,
                    posterPath,
                    releaseDate,
                    voteCount
                )
                list.add(tvShowResponse)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun getTvShow(tvShowId: Int): TvShowResponse? {
        var tvShowResponse: TvShowResponse? = null

        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponse.json"))
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)
                val id = tvShow.getInt("id")

                if (id == tvShowId) {
                    val title = tvShow.getString("title")
                    val overview = tvShow.getString("overview")
                    val voteAverage = tvShow.getDouble("vote_average")
                    val posterPath = tvShow.getString("poster_path")
                    val releaseDate = tvShow.getString("release_date")
                    val voteCount = tvShow.getInt("vote_count")

                    tvShowResponse = TvShowResponse(
                        id,
                        title,
                        overview,
                        voteAverage,
                        posterPath,
                        releaseDate,
                        voteCount
                    )
                }
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return tvShowResponse
    }
}
package com.naufalprakoso.moviesjetpack.data.source.remote

import android.app.Application
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse
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

    fun getMovie(movieId: String?): MovieResponse? {
        var movieResponse: MovieResponse? = null

        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponse.json"))
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)
                val id = movie.getString("id")

                if (id == movieId) {
                    val title = movie.getString("title")
                    val overview = movie.getString("overview")
                    val rating = movie.getString("rating")
                    val genre = movie.getString("genre")
                    val image = movie.getString("image")
                    val year = movie.getString("year")
                    val duration = movie.getString("duration")

                    movieResponse = MovieResponse(id, title, overview, rating, genre, image, year, duration)
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

                val id = movie.getString("id")
                val title = movie.getString("title")
                val overview = movie.getString("overview")
                val rating = movie.getString("rating")
                val genre = movie.getString("genre")
                val image = movie.getString("image")
                val year = movie.getString("year")
                val duration = movie.getString("duration")

                val movieResponse = MovieResponse(id, title, overview, rating, genre, image, year, duration)
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

                val id = tvShow.getString("id")
                val title = tvShow.getString("title")
                val overview = tvShow.getString("overview")
                val rating = tvShow.getString("rating")
                val genre = tvShow.getString("genre")
                val image = tvShow.getString("image")
                val year = tvShow.getString("year")
                val episode = tvShow.getString("episode")

                val tvShowResponse = TvShowResponse(id, title, overview, rating, genre, image, year, episode)
                list.add(tvShowResponse)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun getTvShow(tvShowId: String?): TvShowResponse? {
        var tvShowResponse: TvShowResponse? = null

        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponse.json"))
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)
                val id = tvShow.getString("id")

                if (id == tvShowId) {
                    val title = tvShow.getString("title")
                    val overview = tvShow.getString("overview")
                    val rating = tvShow.getString("rating")
                    val genre = tvShow.getString("genre")
                    val image = tvShow.getString("image")
                    val year = tvShow.getString("year")
                    val episode = tvShow.getString("episode")

                    tvShowResponse = TvShowResponse(id, title, overview, rating, genre, image, year, episode)
                }
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return tvShowResponse
    }
}
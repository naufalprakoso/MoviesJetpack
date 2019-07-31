package com.naufalprakoso.moviesjetpack.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.data.source.MovieRepository
import com.naufalprakoso.moviesjetpack.utils.Const
import com.naufalprakoso.moviesjetpack.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*

class DetailMovieActivity(
    private val movieRepository: MovieRepository? = null
) : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progress_bar.visibility = View.VISIBLE

        val movieType = intent.getStringExtra(Const.MOVIE_TYPE)
        viewModel = obtainViewModel(this)
        viewModel.movieId = intent.getStringExtra(Const.DETAIL_MOVIE)

        if (movieType == "movie") {
            viewModel.getMovie()?.observe(this, Observer { movie ->
                progress_bar.visibility = View.GONE
                tv_title.text = movie?.title
                tv_overview.text = movie?.overview
                tv_genre.text = movie?.genre

                Glide.with(this)
                    .load(movie?.image)
                    .into(img_poster)

                tv_year_duration.text = "(${movie?.year}) - ${movie?.duration}"
            })
        } else {
            viewModel.getTvShow()?.observe(this, Observer { tvShow ->
                progress_bar.visibility = View.GONE
                tv_title.text = tvShow?.title
                tv_overview.text = tvShow?.overview
                tv_genre.text = tvShow?.genre

                Glide.with(this)
                    .load(tvShow?.image)
                    .into(img_poster)

                tv_year_duration.text = "(${tvShow?.year}) - ${tvShow?.episode} episode(s)"
            })
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Added to your favorite list", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val viewModelFactory = ViewModelFactory()
        val factory = viewModelFactory.getInstanceDetail(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
    }

}

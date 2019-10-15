package com.naufalprakoso.moviesjetpack.ui.favorite.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteMovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import com.naufalprakoso.moviesjetpack.ui.detail.DetailViewModel
import com.naufalprakoso.moviesjetpack.utils.Const
import com.naufalprakoso.moviesjetpack.viewmodel.ViewModelFactory
import com.naufalprakoso.moviesjetpack.vo.Status

import kotlinx.android.synthetic.main.activity_favorite_detail.fab
import kotlinx.android.synthetic.main.activity_favorite_detail.toolbar
import kotlinx.android.synthetic.main.content_favorite_detail.*

class FavoriteDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progress_bar.visibility = View.VISIBLE

        val movieType = intent.getStringExtra(Const.MOVIE_TYPE)
        viewModel = obtainViewModel(this)
        viewModel.movieId = intent.getIntExtra(Const.MOVIE_ID, 0)
        viewModel.setUsername("Naufal Prakoso")

        if (movieType == "movie") {
            viewModel.getFavoriteMovie().observe(this, Observer {
                when (it.status) {
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        val movie = it.data

                        progress_bar.visibility = View.GONE
                        tv_title.text = movie?.title
                        tv_overview.text = movie?.overview

                        Glide.with(this)
                            .load("https://image.tmdb.org/t/p/w185/${movie?.poster_path}")
                            .into(img_poster)

                        tv_vote_average.text = movie?.vote_average.toString()
                        tv_vote_count.text = "(${movie?.vote_count})"

                        viewModel.checkFavoriteMoviesState().observe(this, Observer { checkData ->
                            when (checkData.status) {
                                Status.LOADING -> {
                                    fab.isEnabled = false
                                }
                                Status.SUCCESS -> {
                                    fab.isEnabled = true

                                    if (checkData.data?.size!! > 0) {
                                        fab.setImageResource(R.drawable.ic_star_white)

                                        fab.setOnClickListener {
                                            viewModel.unsetFavoriteMovie(checkData.data[0])
                                            Toast.makeText(
                                                this,
                                                "Deleted from your favorite list",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            finish()
                                        }
                                    } else {
                                        fab.setOnClickListener { view ->
                                            val movieTvShow = movie?.let { movie ->
                                                FavoriteMovieEntity(
                                                    movie.id,
                                                    movie.title,
                                                    movie.overview,
                                                    movie.vote_average,
                                                    movie.poster_path,
                                                    movie.release_date,
                                                    movie.vote_count
                                                )
                                            }

                                            viewModel.setFavoriteMovie(movieTvShow)
                                            Snackbar.make(
                                                view,
                                                "Added to your favorite list",
                                                Snackbar.LENGTH_LONG
                                            )
                                                .setAction("Action", null).show()

                                            fab.setImageResource(R.drawable.ic_star_white)
                                        }
                                    }
                                }
                                Status.ERROR -> {
                                    fab.isEnabled = false
                                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        })
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else {
            viewModel.getFavoriteTvShow().observe(this, Observer {
                when (it.status) {
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        val tvShow = it.data

                        progress_bar.visibility = View.GONE
                        tv_title.text = tvShow?.title
                        tv_overview.text = tvShow?.overview

                        Glide.with(this)
                            .load("https://image.tmdb.org/t/p/w185/${tvShow?.poster_path}")
                            .into(img_poster)

                        tv_vote_average.text = tvShow?.vote_average.toString()
                        tv_vote_count.text = "(${tvShow?.vote_count})"

                        viewModel.checkFavoriteTvShowsState().observe(this, Observer { checkData ->
                            when (checkData.status) {
                                Status.LOADING -> {
                                    fab.isEnabled = false
                                }
                                Status.SUCCESS -> {
                                    fab.isEnabled = true

                                    if (checkData.data?.size!! > 0) {
                                        fab.setImageResource(R.drawable.ic_star_white)

                                        fab.setOnClickListener {
                                            viewModel.unsetFavoriteTvShow(checkData.data[0])
                                            Toast.makeText(
                                                this,
                                                "Deleted from your favorite list",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            finish()
                                        }
                                    } else {
                                        fab.setOnClickListener { view ->
                                            val movieTvShow = tvShow?.let { tvShow ->
                                                FavoriteTvShowEntity(
                                                    tvShow.id,
                                                    tvShow.title,
                                                    tvShow.overview,
                                                    tvShow.vote_average,
                                                    tvShow.poster_path,
                                                    tvShow.release_date,
                                                    tvShow.vote_count
                                                )
                                            }

                                            viewModel.setFavoriteTvShow(movieTvShow)
                                            Snackbar.make(
                                                view,
                                                "Added to your favorite list",
                                                Snackbar.LENGTH_LONG
                                            )
                                                .setAction("Action", null).show()

                                            fab.setImageResource(R.drawable.ic_star_white)
                                        }
                                    }
                                }
                                Status.ERROR -> {
                                    fab.isEnabled = false
                                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        })
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
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

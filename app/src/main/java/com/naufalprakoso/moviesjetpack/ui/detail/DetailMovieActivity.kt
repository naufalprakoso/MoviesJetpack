package com.naufalprakoso.moviesjetpack.ui.detail

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
import com.naufalprakoso.moviesjetpack.utils.Const
import com.naufalprakoso.moviesjetpack.viewmodel.ViewModelFactory
import com.naufalprakoso.moviesjetpack.vo.Status

import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.progress_bar

class DetailMovieActivity : AppCompatActivity() {

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
        viewModel.setUsername("Naufal Prakoso")

        if (movieType == "movie") {
            viewModel.getMovie().observe(this, Observer {
                when (it.status) {
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        val movie = it.data

                        progress_bar.visibility = View.GONE
                        tv_title.text = movie?.title
                        tv_overview.text = movie?.overview
                        tv_genre.text = movie?.genre

                        Glide.with(this)
                            .load(movie?.image)
                            .into(img_poster)

                        tv_year_duration.text = "(${movie?.year}) - ${movie?.duration}"

                        viewModel.checkFavoriteMoviesState().observe(this, Observer { checkData ->
                            when (checkData.status) {
                                Status.LOADING -> {
                                    fab.isEnabled = false
                                }
                                Status.SUCCESS -> {
                                    fab.isEnabled = true

                                    if (checkData.data?.size!! > 0) {
                                        fab.setImageResource(R.drawable.ic_star_white)

                                        fab.setOnClickListener { view ->
                                            viewModel.unsetFavoriteMovie(checkData.data[0])
                                            Snackbar.make(view, "Deleted from your favorite list", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show()

                                            fab.setImageResource(R.drawable.ic_star_border_white)
                                        }
                                    } else {
                                        fab.setOnClickListener { view ->
                                            val movieTvShow = FavoriteMovieEntity(
                                                movie?.id.toString(),
                                                movie?.title,
                                                movie?.overview,
                                                movie?.rating,
                                                movie?.genre,
                                                movie?.image,
                                                movie?.year,
                                                movie?.duration
                                            )

                                            viewModel.setFavoriteMovie(movieTvShow)
                                            Snackbar.make(view, "Added to your favorite list", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show()

                                            fab.setImageResource(R.drawable.ic_star_white)
                                        }
                                    }
                                }
                                Status.ERROR -> {
                                    fab.isEnabled = false
                                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
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
            viewModel.getTvShow().observe(this, Observer {
                when (it.status) {
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        val tvShow = it.data

                        progress_bar.visibility = View.GONE
                        tv_title.text = tvShow?.title
                        tv_overview.text = tvShow?.overview
                        tv_genre.text = tvShow?.genre

                        Glide.with(this)
                            .load(tvShow?.image)
                            .into(img_poster)

                        tv_year_duration.text = "(${tvShow?.year}) - ${tvShow?.episode} episode(s)"

                        viewModel.checkFavoriteTvShowsState().observe(this, Observer { checkData ->
                            when (checkData.status) {
                                Status.LOADING -> {
                                    fab.isEnabled = false
                                }
                                Status.SUCCESS -> {
                                    fab.isEnabled = true

                                    if (checkData.data?.size!! > 0) {
                                        fab.setImageResource(R.drawable.ic_star_white)

                                        fab.setOnClickListener { view ->
                                            viewModel.unsetFavoriteTvShow(checkData.data[0])
                                            Snackbar.make(view, "Deleted from your favorite list", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show()

                                            fab.setImageResource(R.drawable.ic_star_border_white)
                                        }
                                    } else {
                                        fab.setOnClickListener { view ->
                                            val movieTvShow = FavoriteTvShowEntity(
                                                tvShow?.id.toString(),
                                                tvShow?.title,
                                                tvShow?.overview,
                                                tvShow?.rating,
                                                tvShow?.genre,
                                                tvShow?.image,
                                                tvShow?.year,
                                                tvShow?.episode
                                            )

                                            viewModel.setFavoriteTvShow(movieTvShow)
                                            Snackbar.make(view, "Added to your favorite list", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show()

                                            fab.setImageResource(R.drawable.ic_star_white)
                                        }
                                    }
                                }
                                Status.ERROR -> {
                                    fab.isEnabled = false
                                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
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
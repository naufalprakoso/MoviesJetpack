package com.naufalprakoso.moviesjetpack.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.ui.favorite.detail.FavoriteDetailActivity
import com.naufalprakoso.moviesjetpack.ui.movie.MovieViewModel
import com.naufalprakoso.moviesjetpack.utils.Const
import com.naufalprakoso.moviesjetpack.viewmodel.ViewModelFactory
import com.naufalprakoso.moviesjetpack.vo.Status
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.jetbrains.anko.startActivity

class FavoriteMovieFragment : Fragment() {

    private lateinit var adapter: FavoriteMoviePagedAdapter
    private var viewModel: MovieViewModel? = null

    companion object {
        fun newInstance(): Fragment {
            return FavoriteMovieFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            progress_bar.visibility = View.VISIBLE
            viewModel = obtainViewModel(activity)

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)

            viewModel?.getFavoriteMoviesPaged()?.removeObservers(this)
            viewModel?.setUsername("Naufal Prakoso")
            viewModel?.getFavoriteMoviesPaged()?.observe(this, Observer { it ->
                when (it.status) {
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE

                        if (it.data?.isEmpty()!!) {
                            txt_empty.visibility = View.VISIBLE
                            rv_movie.visibility = View.GONE
                        } else {
                            txt_empty.visibility = View.GONE
                            rv_movie.visibility = View.VISIBLE

                            adapter = FavoriteMoviePagedAdapter(it.data) {
                                context?.startActivity<FavoriteDetailActivity>(
                                    Const.DETAIL_MOVIE to it.id,
                                    Const.MOVIE_TYPE to "movie"
                                )
                            }
                            adapter.submitList(it.data)
                            adapter.notifyDataSetChanged()
                            rv_movie.adapter = adapter
                        }

                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): MovieViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(MovieViewModel::class.java) }
    }

}

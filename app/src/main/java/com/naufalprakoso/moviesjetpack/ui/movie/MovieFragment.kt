package com.naufalprakoso.moviesjetpack.ui.movie

import android.content.Intent
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
import com.naufalprakoso.moviesjetpack.ui.detail.DetailMovieActivity
import com.naufalprakoso.moviesjetpack.utils.Const
import com.naufalprakoso.moviesjetpack.viewmodel.ViewModelFactory
import com.naufalprakoso.moviesjetpack.vo.Status
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject
import androidx.lifecycle.ViewModelProvider

class MovieFragment : Fragment() {

    private lateinit var adapter: MoviePagedAdapter
    private var viewModel: MovieViewModel? = null

    @Inject
    var factory: ViewModelProvider.Factory? = null

    companion object {

        fun newInstance(): Fragment {
            return MovieFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            progress_bar.visibility = View.VISIBLE
            viewModel = obtainViewModel(activity)

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.setHasFixedSize(true)

            viewModel?.getMoviesPaged()?.removeObservers(this)
            viewModel?.setUsername("Naufal Prakoso")
            viewModel?.getMoviesPaged()?.observe(this, Observer { it ->
                when (it.status) {
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE

                        adapter = MoviePagedAdapter(it.data!!) {
                            val intent = Intent(context, DetailMovieActivity::class.java).apply {
                                putExtra(Const.MOVIE_ID, it.id)
                                putExtra(Const.MOVIE_TYPE, "movie")
                            }

                            context?.startActivity(intent)
                        }
                        adapter.submitList(it.data)
                        adapter.notifyDataSetChanged()
                        rv_movie.adapter = adapter
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
        factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(MovieViewModel::class.java) }
    }
}

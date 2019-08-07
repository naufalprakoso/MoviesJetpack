package com.naufalprakoso.moviesjetpack.ui.movie

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
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.startActivity

class MovieFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private var viewModel: MovieViewModel? = null

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

            viewModel?.getMovies()?.removeObservers(this)
            viewModel?.getMovies()?.observe(viewLifecycleOwner, Observer { movies ->
                progress_bar.visibility = View.GONE
                adapter = MovieAdapter(movies) {
                    context?.startActivity<DetailMovieActivity>(
                        Const.DETAIL_MOVIE to it.id,
                        Const.MOVIE_TYPE to "movie"
                    )
                }
                adapter.notifyDataSetChanged()
                rv_movie.adapter = adapter
            })
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): MovieViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(MovieViewModel::class.java) }
    }
}

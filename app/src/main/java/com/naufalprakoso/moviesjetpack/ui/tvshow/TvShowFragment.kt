package com.naufalprakoso.moviesjetpack.ui.tvshow

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
import org.jetbrains.anko.startActivity
import android.widget.Toast

class TvShowFragment : Fragment() {

    private lateinit var adapter: TvShowAdapter
    private var viewModel: TvShowViewModel? = null

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

            viewModel?.getTvShows()?.removeObservers(this)
            viewModel?.setUsername("Naufal Prakoso")
            viewModel?.getTvShows()?.observe(this, Observer { it ->
                when(it.status){
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE

                        adapter = TvShowAdapter(it.data!!) {
                            context?.startActivity<DetailMovieActivity>(
                                Const.DETAIL_MOVIE to it.id,
                                Const.MOVIE_TYPE to "tv_show"
                            )
                        }
                        adapter.notifyDataSetChanged()
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
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(TvShowViewModel::class.java) }
    }
}

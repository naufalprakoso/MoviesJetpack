package com.naufalprakoso.moviesjetpack.ui.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import kotlinx.android.synthetic.main.items_movie.view.*

class MoviePagedAdapter(
    private val movies: List<MovieEntity>,
    private val listener: (MovieEntity) -> Unit
) : PagedListAdapter<MovieEntity, MoviePagedAdapter.ViewHolder>(MoviesDiffCallback) {

    companion object {
        val MoviesDiffCallback = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(movies[position], listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindItem(movie: MovieEntity, listener: (MovieEntity) -> Unit) {

            itemView.tv_title.text = movie.title

            movie.overview?.let {
                if (it.length > 100) {
                    itemView.tv_overview.text = "${it.substring(0, 100)}..."
                } else {
                    itemView.tv_overview.text = movie.overview
                }
            }

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w185/${movie.poster_path}")
                .into(itemView.img_poster)

            itemView.cv_movie.setOnClickListener {
                listener(movie)
            }
        }
    }
}
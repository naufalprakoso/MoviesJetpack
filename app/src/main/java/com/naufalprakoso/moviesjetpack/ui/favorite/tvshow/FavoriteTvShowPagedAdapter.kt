package com.naufalprakoso.moviesjetpack.ui.favorite.tvshow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.data.source.local.entity.FavoriteTvShowEntity
import kotlinx.android.synthetic.main.items_movie.view.*
import org.jetbrains.anko.sdk25.listeners.onClick

class FavoriteTvShowPagedAdapter(
    private val movies: List<FavoriteTvShowEntity>,
    private val listener: (FavoriteTvShowEntity) -> Unit
) : PagedListAdapter<FavoriteTvShowEntity, FavoriteTvShowPagedAdapter.ViewHolder>(TvShowsDiffCallback) {

    companion object {
        val TvShowsDiffCallback = object : DiffUtil.ItemCallback<FavoriteTvShowEntity>() {
            override fun areItemsTheSame(oldItem: FavoriteTvShowEntity, newItem: FavoriteTvShowEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FavoriteTvShowEntity, newItem: FavoriteTvShowEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_movie,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(movies[position], listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindItem(movie: FavoriteTvShowEntity, listener: (FavoriteTvShowEntity) -> Unit) {
            itemView.tv_title.text = movie.title

            if (movie.overview?.length!! > 100) {
                itemView.tv_overview.text = "${movie.overview.substring(0, 100)}..."
            } else {
                itemView.tv_overview.text = movie.overview
            }

            Glide.with(itemView.context)
                .load(movie.image)
                .into(itemView.img_poster)

            itemView.cv_movie.onClick {
                listener(movie)
            }
        }
    }
}
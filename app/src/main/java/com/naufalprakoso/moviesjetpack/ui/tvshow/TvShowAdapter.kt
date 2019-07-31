package com.naufalprakoso.moviesjetpack.ui.tvshow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import kotlinx.android.synthetic.main.items_movie.view.*
import org.jetbrains.anko.sdk25.listeners.onClick

class TvShowAdapter(
    private val tvShows: List<TvShowEntity>,
    private val listener: (TvShowEntity) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false))

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(tvShows[position], listener)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindItem(tvShow: TvShowEntity, listener: (TvShowEntity) -> Unit){
            itemView.tv_title.text = tvShow.title

            if (tvShow.overview?.length!! > 100){
                itemView.tv_overview.text = "${tvShow.overview.substring(0, 100)}..."
            }else{
                itemView.tv_overview.text = tvShow.overview
            }

            Glide.with(itemView.context)
                .load(tvShow.image)
                .into(itemView.img_poster)

            itemView.cv_movie.onClick {
                listener(tvShow)
            }
        }
    }
}
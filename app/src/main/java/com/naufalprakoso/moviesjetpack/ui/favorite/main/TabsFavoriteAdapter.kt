package com.naufalprakoso.moviesjetpack.ui.favorite.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.naufalprakoso.moviesjetpack.ui.favorite.movie.FavoriteMovieFragment
import com.naufalprakoso.moviesjetpack.ui.favorite.tvshow.FavoriteTvShowFragment

class TabsFavoriteAdapter(
    fm: FragmentManager,
    private var mNumOfTabs: Int
) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return mNumOfTabs
    }

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> FavoriteMovieFragment.newInstance()
            1 -> FavoriteTvShowFragment.newInstance()
            else -> null
        }
    }
}
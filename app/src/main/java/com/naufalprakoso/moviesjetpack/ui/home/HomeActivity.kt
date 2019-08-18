package com.naufalprakoso.moviesjetpack.ui.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.naufalprakoso.moviesjetpack.R
import com.naufalprakoso.moviesjetpack.ui.favorite.FavoriteMovieFragment
import com.naufalprakoso.moviesjetpack.ui.movie.MovieFragment
import com.naufalprakoso.moviesjetpack.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val SELECTED_MENU = "selected_menu"
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_movie -> {
                fragment = MovieFragment.newInstance()
            }
            R.id.navigation_tv_show -> {
                fragment = TvShowFragment.newInstance()
            }
            R.id.navigation_favorite -> {
                fragment = FavoriteMovieFragment.newInstance()
            }
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.frame_container, fragment).commit()
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if(savedInstanceState != null){
            savedInstanceState.getInt(SELECTED_MENU)
        }else{
            nav_view.selectedItemId = R.id.navigation_movie
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_MENU, nav_view.selectedItemId)
    }
}

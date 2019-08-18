package com.naufalprakoso.moviesjetpack.ui.favorite.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufalprakoso.moviesjetpack.R
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return FavoriteFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){
            val tabLayout = activity?.findViewById(R.id.tab_layout) as TabLayout
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.favorite_movie)))
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.favorite_tv_show)))
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL

            val viewPager = activity?.findViewById(R.id.view_pager) as ViewPager
            val tabsAdapter = activity?.supportFragmentManager?.let {
                TabsFavoriteAdapter(it, tabLayout.tabCount)
            }
            viewPager.adapter = tabsAdapter
            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }
}

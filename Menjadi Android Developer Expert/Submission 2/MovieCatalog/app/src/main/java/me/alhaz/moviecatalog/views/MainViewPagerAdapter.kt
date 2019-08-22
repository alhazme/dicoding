package me.alhaz.moviecatalog.views

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.views.movies.list.MovieListFragment
import me.alhaz.moviecatalog.views.tvshows.list.TVShowListFragment

class MainViewPagerAdapter(val fragmentManager: FragmentManager, val context: Context): FragmentPagerAdapter(fragmentManager) {

    private val pages = listOf(
        MovieListFragment(),
        TVShowListFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.resources.getString(R.string.movie)
            else -> context.resources.getString(R.string.tv_show)
        }
    }

}
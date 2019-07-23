package me.alhaz.snippet.movieapp.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import me.alhaz.snippet.movieapp.views.movies.list.MovieListFragment
import me.alhaz.snippet.movieapp.views.tvshows.list.TVShowListFragment

class MainViewPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

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
            0 -> "Movies"
            else -> "TV Shows"
        }
    }

}
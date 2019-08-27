package me.alhaz.moviecatalog.views

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.views.movies.list.MovieListFragment
import me.alhaz.moviecatalog.views.tvshows.list.TVShowListFragment

class MainViewPagerAdapter(private val context: Context, fragmentManager: FragmentManager?): FragmentPagerAdapter(fragmentManager) {

    val pages = mutableListOf<Fragment>()

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.movies)
            else -> context.getString(R.string.tv_shows)
        }
    }

    fun addFragment(fragment: Fragment) {
        pages.add(fragment)
    }

}
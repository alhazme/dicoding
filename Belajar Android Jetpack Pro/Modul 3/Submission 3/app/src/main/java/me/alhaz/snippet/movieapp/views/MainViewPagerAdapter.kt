package me.alhaz.snippet.movieapp.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class MainViewPagerAdapter(fragmentManager: FragmentManager?): FragmentPagerAdapter(fragmentManager) {

    val pages = mutableListOf<Fragment>()

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

    fun addFragment(fragment: Fragment) {
        pages.add(fragment)
    }

}
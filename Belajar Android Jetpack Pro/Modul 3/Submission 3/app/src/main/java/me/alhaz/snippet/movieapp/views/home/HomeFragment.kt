package me.alhaz.snippet.movieapp.views.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_home.*
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.views.MainViewPagerAdapter
import me.alhaz.snippet.movieapp.views.movies.list.MovieListFragment
import me.alhaz.snippet.movieapp.views.tvshows.list.TVShowListFragment

class HomeFragment : Fragment() {

    private val movieListFragment = MovieListFragment()
    private val tvShowListFragment = TVShowListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLayout()
    }

    private fun setupLayout() {
        activity?.let { fragmentActivity ->
            val adapter = MainViewPagerAdapter(childFragmentManager)
            adapter.addFragment(movieListFragment)
            adapter.addFragment(tvShowListFragment)
            vp_container.adapter = adapter
            tl_container.setupWithViewPager(vp_container)
        }
    }

}

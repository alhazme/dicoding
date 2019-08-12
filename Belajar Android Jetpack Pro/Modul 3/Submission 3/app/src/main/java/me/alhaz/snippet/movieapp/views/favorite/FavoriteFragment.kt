package me.alhaz.snippet.movieapp.views.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.views.MainViewPagerAdapter
import me.alhaz.snippet.movieapp.views.movies.favorite.MovieFavoriteFragment
import me.alhaz.snippet.movieapp.views.tvshows.favorite.TVShowFavoriteFragment

class FavoriteFragment : Fragment() {

    private val movieFavoriteFragment = MovieFavoriteFragment()
    private val tvShowFavoriteFragment = TVShowFavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLayout()
    }

    private fun setupLayout() {
        activity?.let { fragmentActivity ->
            val adapter = MainViewPagerAdapter(childFragmentManager)
            adapter.addFragment(movieFavoriteFragment)
            adapter.addFragment(tvShowFavoriteFragment)
            vp_container.adapter = adapter
            tl_container.setupWithViewPager(vp_container)
        }
    }


}

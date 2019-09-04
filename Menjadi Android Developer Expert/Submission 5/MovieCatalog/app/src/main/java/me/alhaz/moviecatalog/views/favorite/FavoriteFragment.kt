package me.alhaz.moviecatalog.views.favorite

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.tl_container
import kotlinx.android.synthetic.main.fragment_favorite.toolbar
import kotlinx.android.synthetic.main.fragment_favorite.vp_container
import kotlinx.android.synthetic.main.fragment_home.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.views.MainViewPagerAdapter
import me.alhaz.moviecatalog.views.movies.favorite.MovieFavoriteFragment
import me.alhaz.moviecatalog.views.tvshows.favorite.TVShowFavoriteFragment

class FavoriteFragment : Fragment() {

    private val movieFavoriteFragment = MovieFavoriteFragment()
    private val tvShowFavoriteFragment = TVShowFavoriteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.let {inflater ->
            menu?.let { menu ->
                inflater.inflate(R.menu.main_menu, menu)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let { item ->
            if (item.getItemId() === me.alhaz.moviecatalog.R.id.action_change_settings) {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupLayout() {
        activity?.let { fragmentActivity ->

            (fragmentActivity as AppCompatActivity).setSupportActionBar(toolbar)
            (fragmentActivity as AppCompatActivity).supportActionBar?.let { actionBar ->
                actionBar.setDisplayHomeAsUpEnabled(false)
            }

            val adapter = MainViewPagerAdapter(fragmentActivity, childFragmentManager)
            adapter.addFragment(movieFavoriteFragment)
            adapter.addFragment(tvShowFavoriteFragment)
            vp_container.adapter = adapter
            tl_container.setupWithViewPager(vp_container)
        }
    }


}

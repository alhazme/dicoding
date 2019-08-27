package me.alhaz.moviecatalog.views.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.views.MainViewPagerAdapter
import me.alhaz.moviecatalog.views.movies.list.MovieListFragment
import me.alhaz.moviecatalog.views.tvshows.list.TVShowListFragment

class HomeFragment : Fragment() {

    private val movieListFragment = MovieListFragment()
    private val tvShowListFragment = TVShowListFragment()

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.let {inflater ->
            menu?.let { menu ->
                inflater.inflate(R.menu.main_menu, menu)
                setupSearchView(menu)
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
            adapter.addFragment(movieListFragment)
            adapter.addFragment(tvShowListFragment)
            vp_container.adapter = adapter
            tl_container.setupWithViewPager(vp_container)

        }
    }

    private fun setupSearchView(menu: Menu) {
        val searchMenu = menu.findItem(R.id.action_search)
        searchView = searchMenu.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {

                }
                return true
            }

        })
    }

}

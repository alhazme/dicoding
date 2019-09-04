package me.alhaz.moviecatalog.views.tvshows.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_list.progressbar
import kotlinx.android.synthetic.main.fragment_tvshow_list.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.valueobject.Status
import me.alhaz.moviecatalog.viewmodels.TVShowViewModelFactory
import me.alhaz.moviecatalog.views.tvshows.detail.TVShowDetailActivity

class TVShowListFragment : Fragment() {

    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var viewModel: TVShowListViewModel

    private lateinit var searchView: SearchView
    private var defaultTVShows = ArrayList<TVShow>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_tvshow_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            fragmentActivity = it
            setupToolbar(it)
            setupViewModel(it)
            setupAdapter(it)
        }
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

    private fun setupSearchView(menu: Menu) {
        val searchMenu = menu.findItem(R.id.action_search)
        searchView = searchMenu.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    progressbar.visibility = View.VISIBLE
                    rv_tvshows.visibility = View.GONE
                    if (it.length > 0) {
                        viewModel.searchTVShow(it).observe(fragmentActivity, Observer { tvShows ->
                            tvShowListAdapter.removeAllTVShows()
                            if (tvShows.size > 0) {
                                progressbar.visibility = View.GONE
                                rv_tvshows.visibility = View.VISIBLE
                                tvShowListAdapter.addTVShows(tvShows)
                            }
                            else {
                                progressbar.visibility = View.GONE
                                rv_tvshows.visibility = View.GONE
                            }
                        })
                    }
                    else {
                        tvShowListAdapter.removeAllTVShows()
                        if (defaultTVShows.size > 0) {
                            progressbar.visibility = View.GONE
                            rv_tvshows.visibility = View.VISIBLE
                            tvShowListAdapter.addTVShows(defaultTVShows)
                        }
                        else {
                            progressbar.visibility = View.GONE
                            rv_tvshows.visibility = View.GONE
                        }
                    }
                }
                val keyboard = fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(View(fragmentActivity).windowToken, 0)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (newText.length == 0) {
                        tvShowListAdapter.removeAllTVShows()
                        if (defaultTVShows.size > 0) {
                            progressbar.visibility = View.GONE
                            rv_tvshows.visibility = View.VISIBLE
                            tvShowListAdapter.addTVShows(defaultTVShows)
                        }
                        else {
                            progressbar.visibility = View.GONE
                            rv_tvshows.visibility = View.GONE
                        }
                    }
                }
                return false
            }

        })
    }

    private fun obtainViewModel(activity: FragmentActivity): TVShowListViewModel {
        val factory = TVShowViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TVShowListViewModel::class.java)
    }

    private fun setupViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(activity)
        viewModel.getTVShowList().observe(this, Observer { tvShows ->
            defaultTVShows = tvShows
            if (tvShows.size > 0) {
                progressbar.visibility = View.GONE
                rv_tvshows.visibility = View.VISIBLE
                tvShowListAdapter.addTVShows(tvShows)
            }
            else {
                progressbar.visibility = View.GONE
                rv_tvshows.visibility = View.GONE
                tvShowListAdapter.removeAllTVShows()
            }

        })
    }

    private fun setupLayout() {
        rv_tvshows.setHasFixedSize(true)
        rv_tvshows.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupToolbar(fragmentActivity: FragmentActivity) {
        (fragmentActivity as AppCompatActivity).setSupportActionBar(toolbar)
        (fragmentActivity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun setupAdapter(context: Context) {
        tvShowListAdapter = TVShowListAdapter(context, clickListener = { tvShow ->
            openDetailTVShowPage(tvShow.id)
        })
        rv_tvshows.adapter = tvShowListAdapter
    }


    private fun openDetailTVShowPage(tvshowID: Long) {
        activity?.let {
            val intent = Intent(it, TVShowDetailActivity::class.java)
            intent.putExtra("tvshow_id", tvshowID)
            it.startActivity(intent)
        }
    }


}

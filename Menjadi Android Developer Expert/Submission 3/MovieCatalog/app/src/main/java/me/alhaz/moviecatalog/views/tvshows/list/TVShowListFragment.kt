package me.alhaz.moviecatalog.views.tvshows.list

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_movie_list.progressbar
import kotlinx.android.synthetic.main.fragment_tvshow_list.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.data.DataDummy
import me.alhaz.moviecatalog.model.TVShow
import me.alhaz.moviecatalog.viewmodels.TVShowViewModelFactory
import me.alhaz.moviecatalog.views.tvshows.detail.TVShowDetailActivity

class TVShowListFragment : Fragment() {

    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var viewModel: TVShowListViewModel

    private val tvShows = ArrayList<TVShow>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            setupViewModel(it)
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): TVShowListViewModel {
        val factory = TVShowViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TVShowListViewModel::class.java)
    }

    private fun setupViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(activity)
        viewModel.getTVShowList().observe(this, Observer { tvShowsLiveData ->
            tvShows.addAll(tvShowsLiveData)
            showData(activity)
        })
    }

    private fun setupLayout() {
        rv_tvshows.setHasFixedSize(true)
        rv_tvshows.layoutManager = LinearLayoutManager(activity)
    }

    private fun showData(context: Context) {
        tvShowListAdapter = TVShowListAdapter(context, tvShows, clickListener = { tvShow ->
            openDetailMoviePage(tvShow)
        })
        rv_tvshows.adapter = tvShowListAdapter
        if (tvShows.size > 0) {
            progressbar.visibility = View.GONE
            rv_tvshows.visibility = View.VISIBLE
        }
        else {
            progressbar.visibility = View.VISIBLE
            rv_tvshows.visibility = View.GONE
        }
    }

    private fun openDetailMoviePage(tvshow: TVShow) {
        activity?.let {
            val intent = Intent(it, TVShowDetailActivity::class.java)
            intent.putExtra("tvshow_id", tvshow.id)
            it.startActivity(intent)
        }
    }


}

package me.alhaz.moviecatalog.views.tvshows.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tvshow_favorite.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.viewmodels.TVShowViewModelFactory
import me.alhaz.moviecatalog.views.tvshows.detail.TVShowDetailActivity

class TVShowFavoriteFragment : Fragment() {private lateinit var tvShowFavoriteAdapter: TVShowFavoriteAdapter
    private lateinit var viewModel: TVShowFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            setupViewModel(it)
            setupAdapter(it)
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): TVShowFavoriteViewModel {
        val factory = TVShowViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TVShowFavoriteViewModel::class.java)
    }

    private fun setupViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(activity)
        viewModel.getTVShowFavorite().observe(this, Observer {
            tvShowFavoriteAdapter.submitList(null)
            tvShowFavoriteAdapter.submitList(it)
            showData(it)
        })
    }

    private fun setupLayout(view: View) {
        rv_favorite_tvshows.setHasFixedSize(true)
        rv_favorite_tvshows.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupAdapter(context: Context) {
        tvShowFavoriteAdapter = TVShowFavoriteAdapter(context, clickListener = { tvShow ->
            openDetailTVShowPage(tvShow.id)
        })
        rv_favorite_tvshows.adapter = tvShowFavoriteAdapter
    }

    private fun showData(tvShows: PagedList<TVShowEntity>) {
        if (tvShows.size > 0) {
            progressbar.visibility = View.GONE
            rv_favorite_tvshows.visibility = View.VISIBLE
        }
        else {
            progressbar.visibility = View.GONE
            rv_favorite_tvshows.visibility = View.GONE
        }
    }

    private fun openDetailTVShowPage(tvShowID: Long) {
        activity?.let {
            val intent = Intent(it, TVShowDetailActivity::class.java)
            intent.putExtra("tvshow_id", tvShowID)
            it.startActivity(intent)
        }
    }


}

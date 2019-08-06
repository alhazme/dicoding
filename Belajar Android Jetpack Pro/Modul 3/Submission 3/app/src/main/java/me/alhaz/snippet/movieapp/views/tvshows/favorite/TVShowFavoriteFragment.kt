package me.alhaz.snippet.movieapp.views.tvshows.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.viewmodels.TVShowViewModelFactory
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.views.tvshows.detail.TVShowDetailActivity

class TVShowFavoriteFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rvTVShows: RecyclerView
    private lateinit var tvShowFavoriteAdapter: TVShowFavoriteAdapter
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
        progressBar = view.findViewById(R.id.progressbar)
        rvTVShows = view.findViewById(R.id.rv_favorite_tvshows)
        rvTVShows.setHasFixedSize(true)
        rvTVShows.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupAdapter(context: Context) {
        tvShowFavoriteAdapter = TVShowFavoriteAdapter(context, clickListener = { tvShow ->
            openDetailTVShowPage(tvShow.id)
        })
        rvTVShows.adapter = tvShowFavoriteAdapter
    }

    private fun showData(tvShows: PagedList<TVShowEntity>) {
        Log.d("1234567890", "showData tvShows.size: ${tvShows.size}")
        if (tvShows.size > 0) {
            progressBar.visibility = View.GONE
            rvTVShows.visibility = View.VISIBLE
        }
        else {
            progressBar.visibility = View.GONE
            rvTVShows.visibility = View.GONE
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

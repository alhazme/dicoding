package me.alhaz.snippet.movieapp.views.tvshows.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.viewmodels.TVShowViewModelFactory
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.valueobject.Status
import me.alhaz.snippet.movieapp.views.tvshows.detail.TVShowDetailActivity

class TVShowListFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rvTVShows: RecyclerView
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var viewModel: TVShowListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow_list, container, false)
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

    private fun obtainViewModel(activity: FragmentActivity): TVShowListViewModel {
        val factory = TVShowViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TVShowListViewModel::class.java)
    }

    private fun setupViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(activity)
        viewModel.getTVShowList().observe(this, Observer { data ->
            data?.let { response ->
                when (response.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        rvTVShows.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        response.data?.let { tvshows ->
                            progressBar.visibility = View.GONE
                            rvTVShows.visibility = View.VISIBLE
                            tvShowListAdapter.submitList(tvshows)
                        }
                    }
                    Status.EMPTY -> {
                        progressBar.visibility = View.GONE
                        rvTVShows.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        rvTVShows.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupLayout(view: View) {
        progressBar = view.findViewById(R.id.progressbar)
        rvTVShows = view.findViewById(R.id.rv_home_tvshows)
        rvTVShows.setHasFixedSize(true)
        rvTVShows.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupAdapter(context: Context) {
        tvShowListAdapter = TVShowListAdapter(context, clickListener = { tvShow ->
            openDetailTVShowPage(tvShow.id)
        })
        rvTVShows.adapter = tvShowListAdapter
    }

    private fun openDetailTVShowPage(tvShowID: Long) {
        activity?.let {
            val intent = Intent(it, TVShowDetailActivity::class.java)
            intent.putExtra("tvshow_id", tvShowID)
            it.startActivity(intent)
        }
    }


}

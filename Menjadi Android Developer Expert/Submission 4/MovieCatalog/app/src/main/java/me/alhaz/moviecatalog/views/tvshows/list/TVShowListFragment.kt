package me.alhaz.moviecatalog.views.tvshows.list

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
                    Status.SUCCESS -> {
                        response.data?.let { tvshows ->
                            progressbar.visibility = View.GONE
                            rv_tvshows.visibility = View.VISIBLE
                            tvShowListAdapter.submitList(tvshows)
                        }
                    }
                    Status.EMPTY -> {
                        progressbar.visibility = View.GONE
                        rv_tvshows.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressbar.visibility = View.GONE
                        rv_tvshows.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupLayout() {
        rv_tvshows.setHasFixedSize(true)
        rv_tvshows.layoutManager = LinearLayoutManager(activity)
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

package me.alhaz.snippet.movieapp.views.tvshows.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.views.tvshows.detail.TVShowDetailActivity

class TVShowListFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rvTVShows: RecyclerView
    private lateinit var tvShowListAdapter: TVShowListAdapter
    private lateinit var viewModel: TVShowListViewModel

    private val tvShows = ArrayList<TVShow>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TVShowListViewModel::class.java)
        EspressoIdlingResource.increment()
        viewModel.getTVShowList().observe(this, Observer {
            tvShows.addAll(it)
            activity?.let {
                showData(it)
                if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
    }

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
            showData(it)
        }
    }

    private fun setupLayout(view: View) {
        progressBar = view.findViewById(R.id.progressbar)
        rvTVShows = view.findViewById(R.id.rv_tvshows)
        rvTVShows.setHasFixedSize(true)
        rvTVShows.layoutManager = LinearLayoutManager(activity)
    }

    private fun showData(context: Context) {
        tvShowListAdapter = TVShowListAdapter(context, tvShows, clickListener = {
            openDetailTVShowPage(it.id)
        })
        rvTVShows.adapter = tvShowListAdapter
        if (tvShows.size > 0) {
            progressBar.visibility = View.GONE
            rvTVShows.visibility = View.VISIBLE
        }
        else {
            progressBar.visibility = View.VISIBLE
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

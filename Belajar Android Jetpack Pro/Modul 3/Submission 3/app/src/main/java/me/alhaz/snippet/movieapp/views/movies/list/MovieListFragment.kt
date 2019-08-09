package me.alhaz.snippet.movieapp.views.movies.list

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
import me.alhaz.snippet.movieapp.views.movies.detail.MovieDetailActivity
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.viewmodels.MovieViewModelFactory
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.valueobject.Status

class MovieListFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
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

    private fun obtainViewModel(activity: FragmentActivity): MovieListViewModel {
        val factory = MovieViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieListViewModel::class.java)
    }

    private fun setupViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(activity)
        viewModel.getListMovie().observe(this, Observer { data ->
            data?.let { response ->
                when (response.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        rvMovies.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        response.data?.let { movies ->
                            progressBar.visibility = View.GONE
                            rvMovies.visibility = View.VISIBLE
                            movieListAdapter.submitList(movies)
                        }
                    }
                    Status.EMPTY -> {
                        progressBar.visibility = View.GONE
                        rvMovies.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        rvMovies.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun setupLayout(view: View) {
        progressBar = view.findViewById(R.id.progressbar)
        rvMovies = view.findViewById(R.id.rv_home_movies)
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupAdapter(context: Context) {
        movieListAdapter = MovieListAdapter(context, clickListener = { movie ->
            openDetailMoviePage(movie.id)
        })
        rvMovies.adapter = movieListAdapter
    }

    private fun openDetailMoviePage(movieID: Long) {
        Log.d("1234567890", "selected movie ID: ${movieID}")
        activity?.let {
            val intent = Intent(it, MovieDetailActivity::class.java)
            intent.putExtra("movie_id", movieID)
            it.startActivity(intent)
        }
    }


}

package me.alhaz.snippet.movieapp.views.movies.favorite

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
import me.alhaz.snippet.movieapp.viewmodels.MovieViewModelFactory
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.views.movies.detail.MovieDetailActivity

class MovieFavoriteFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter
    private lateinit var viewModel: MovieFavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false)
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

    private fun obtainViewModel(activity: FragmentActivity): MovieFavoriteViewModel {
        val factory = MovieViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieFavoriteViewModel::class.java)
    }

    private fun setupViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(activity)
        viewModel.getMovieFavorites().observe(this, Observer {
            movieFavoriteAdapter.submitList(null)
            movieFavoriteAdapter.submitList(it)
            showData(it)
        })
    }

    private fun setupLayout(view: View) {
        progressBar = view.findViewById(R.id.progressbar)
        rvMovies = view.findViewById(R.id.rv_favorite_movies)
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupAdapter(context: Context) {
        movieFavoriteAdapter = MovieFavoriteAdapter(context, clickListener = { movie ->
            openDetailMoviePage(movie.id)
        })
        rvMovies.adapter = movieFavoriteAdapter
    }

    private fun showData(movies: PagedList<MovieEntity>) {
        Log.d("1234567890", "showData movies.size: ${movies.size}")
        if (movies.size > 0) {
            progressBar.visibility = View.GONE
            rvMovies.visibility = View.VISIBLE
        }
        else {
            progressBar.visibility = View.GONE
            rvMovies.visibility = View.GONE
        }
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

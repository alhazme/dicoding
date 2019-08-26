package me.alhaz.moviecatalog.views.movies.favorite

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
import kotlinx.android.synthetic.main.fragment_movie_favorite.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.viewmodels.MovieViewModelFactory
import me.alhaz.moviecatalog.views.movies.detail.MovieDetailActivity

class MovieFavoriteFragment : Fragment() {

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
        rv_favorite_movies.setHasFixedSize(true)
        rv_favorite_movies.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupAdapter(context: Context) {
        movieFavoriteAdapter = MovieFavoriteAdapter(context, clickListener = { movie ->
            openDetailMoviePage(movie.id)
        })
        rv_favorite_movies.adapter = movieFavoriteAdapter
    }

    private fun showData(movies: PagedList<MovieEntity>) {
        if (movies.size > 0) {
            progressbar.visibility = View.GONE
            rv_favorite_movies.visibility = View.VISIBLE
        }
        else {
            progressbar.visibility = View.GONE
            rv_favorite_movies.visibility = View.GONE
        }
    }

    private fun openDetailMoviePage(movieID: Long) {
        activity?.let {
            val intent = Intent(it, MovieDetailActivity::class.java)
            intent.putExtra("movie_id", movieID)
            it.startActivity(intent)
        }
    }


}

package me.alhaz.moviecatalog.views.movies.list

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
import kotlinx.android.synthetic.main.fragment_movie_list.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.data.DataDummy
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.valueobject.Status
import me.alhaz.moviecatalog.viewmodels.MovieViewModelFactory
import me.alhaz.moviecatalog.views.movies.detail.MovieDetailActivity
import me.alhaz.snippet.movieapp.models.Movie

class MovieListFragment : Fragment() {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
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

    private fun obtainViewModel(activity: FragmentActivity): MovieListViewModel {
        val factory = MovieViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieListViewModel::class.java)
    }

    private fun setupViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(activity)
        viewModel.getListMovie().observe(this, Observer { data ->
            data?.let { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        response.data?.let { movies ->
                            progressbar.visibility = View.GONE
                            rv_movies.visibility = View.VISIBLE
                            movieListAdapter.submitList(movies)
                        }
                    }
                    Status.EMPTY -> {
                        progressbar.visibility = View.GONE
                        rv_movies.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressbar.visibility = View.GONE
                        rv_movies.visibility = View.GONE
                    }

                }
            }
        })
    }

    private fun setupLayout() {
        rv_movies.setHasFixedSize(true)
        rv_movies.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupAdapter(context: Context) {
        movieListAdapter = MovieListAdapter(context, clickListener = { movie ->
            openDetailMoviePage(movie.id)
        })
        rv_movies.adapter = movieListAdapter
    }

    private fun openDetailMoviePage(movieID: Long) {
        activity?.let {
            val intent = Intent(it, MovieDetailActivity::class.java)
            intent.putExtra("movie_id", movieID)
            it.startActivity(intent)
        }
    }


}

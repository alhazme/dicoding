package me.alhaz.moviecatalog.views.movies.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_list.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.data.DataDummy
import me.alhaz.moviecatalog.views.movies.detail.MovieDetailActivity
import me.alhaz.snippet.movieapp.models.Movie

class MovieListFragment : Fragment() {

    private lateinit var movieListAdapter: MovieListAdapter

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
            showData(it)
        }
    }

    private fun setupLayout() {
        rv_movies.setHasFixedSize(true)
        rv_movies.layoutManager = LinearLayoutManager(activity)
    }

    private fun showData(context: Context) {
        val movies = DataDummy.generateListMovie()
        movieListAdapter = MovieListAdapter(context, movies, clickListener = { movie ->
            openDetailMoviePage(movie)
        })
        rv_movies.adapter = movieListAdapter
        if (movies.size > 0) {
            progressbar.visibility = View.GONE
            rv_movies.visibility = View.VISIBLE
        }
        else {
            progressbar.visibility = View.VISIBLE
            rv_movies.visibility = View.GONE
        }
    }

    private fun openDetailMoviePage(movie: Movie) {
        activity?.let {
            val intent = Intent(it, MovieDetailActivity::class.java)
            intent.putExtra("movie", movie)
            it.startActivity(intent)
        }
    }


}

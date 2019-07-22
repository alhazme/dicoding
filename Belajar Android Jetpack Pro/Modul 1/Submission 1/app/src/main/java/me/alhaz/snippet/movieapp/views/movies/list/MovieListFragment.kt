package me.alhaz.snippet.movieapp.views.movies.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.views.movies.detail.MovieDetailActivity

class MovieListFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var rvMovies: RecyclerView
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java);
    }

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
            showData(it)
        }
    }

    private fun setupLayout(view: View) {
        progressBar = view.findViewById(R.id.progressbar)
        rvMovies = view.findViewById(R.id.rv_movies)
        rvMovies.setHasFixedSize(true)
        rvMovies.layoutManager = LinearLayoutManager(activity)
    }

    private fun showData(context: Context) {
        val movies = viewModel.getMovieList()
        movieListAdapter = MovieListAdapter(context, movies, clickListener = {
            openDetailMoviePage(it.id)
        })
        rvMovies.adapter = movieListAdapter
        if (movies.size > 0) {
            progressBar.visibility = View.GONE
            rvMovies.visibility = View.VISIBLE
        }
        else {
            progressBar.visibility = View.VISIBLE
            rvMovies.visibility = View.GONE
        }
    }

    private fun openDetailMoviePage(movieID: Int) {
        activity?.let {
            val intent = Intent(it, MovieDetailActivity::class.java)
            intent.putExtra("movie_id", movieID)
            it.startActivity(intent)
        }
    }


}

package me.alhaz.moviecatalog.view.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.data.DataDummy
import me.alhaz.moviecatalog.view.detail.MovieDetailActivity
import me.alhaz.snippet.movieapp.models.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movies: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupData()
        setupLayout()
    }

    fun setupData() {
        movies = DataDummy.listMovies()
    }

    fun setupLayout() {
        movieListAdapter = MovieListAdapter(this, movies, clickListener = { movie ->
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

    fun openDetailMoviePage(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}

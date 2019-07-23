package me.alhaz.snippet.movieapp.views.movies.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie

class MovieListViewModel: ViewModel() {

    var movieRepository: MovieRepository? = null
    private var movies = MutableLiveData<ArrayList<Movie>>()

    init {
        movieRepository = MovieRepository()
    }

    fun getMovies(): MutableLiveData<ArrayList<Movie>> {
        return movies
    }


    fun getMovieList() {
        movieRepository?.let {
            movies = it.getListMovie()
        }
    }

}
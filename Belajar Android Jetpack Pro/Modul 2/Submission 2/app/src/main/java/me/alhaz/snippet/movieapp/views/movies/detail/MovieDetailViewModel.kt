package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie

class MovieDetailViewModel: ViewModel() {

    var movieRepository: MovieRepository? = null
    private var movie = MutableLiveData<Movie>()

    init {
        movieRepository = MovieRepository()
    }

    fun getMovie(): MutableLiveData<Movie> {
        return movie
    }

    fun getMovieDetail(movieID: Long): MutableLiveData<Movie> {
        movieRepository?.let {
            movie = it.getDetailMovie(movieID)
        }
        return movie
    }

}
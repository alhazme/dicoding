package me.alhaz.moviecatalog.views.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.moviecatalog.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.models.Movie

class MovieDetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private var movie = MutableLiveData<Movie>()

    fun getMovieDetail(movieID: Long): LiveData<Movie> {
        if (movie.value == null) {
            movie = movieRepository.getDetailMovie(movieID)
        }
        return movie
    }
}
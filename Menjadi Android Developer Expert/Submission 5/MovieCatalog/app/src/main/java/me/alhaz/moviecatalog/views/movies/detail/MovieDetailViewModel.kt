package me.alhaz.moviecatalog.views.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.moviecatalog.repositories.movies.MovieRepository
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.models.Movie

class MovieDetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private var movie = MutableLiveData<Movie>()

    fun getMovieDetail(movieID: Long): LiveData<Movie> {
        if (movie.value == null) {
            movie = movieRepository.getDetailMovie(movieID)
        }
        return movie
    }

    fun isMovieFavorite(movieID: Long): MovieEntity {
        return movieRepository.isMovieFavorite(movieID)
    }

    fun setMovieFavorite(movie: Movie): MovieEntity {
        return movieRepository.setFavorite(movie)
    }

    fun setMovieUnfavorite(movie: Movie): MovieEntity {
        return movieRepository.setUnfavorite(movie)
    }
}
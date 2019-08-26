package me.alhaz.moviecatalog.views.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.moviecatalog.repositories.movies.MovieRepository
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.models.Movie

class MovieDetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private var movie = MutableLiveData<MovieEntity>()

    fun getMovieDetail(movieID: Long): MutableLiveData<MovieEntity> {
        movie.postValue(movieRepository.getDetailMovie(movieID))
        return movie
    }

    fun setMovieFavorite(movieID: Long): MutableLiveData<MovieEntity> {
        movie.postValue(movieRepository.setFavorite(movieID))
        return movie
    }

    fun setMovieUnfavorite(movieID: Long): MutableLiveData<MovieEntity> {
        movie.postValue(movieRepository.setUnfavorite(movieID))
        return movie
    }
}
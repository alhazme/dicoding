package me.alhaz.snippet.movieapp.views.movies.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity

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
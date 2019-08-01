package me.alhaz.snippet.movieapp.views.movies.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity

class MovieDetailViewModel(application: Application): ViewModel() {

    private var movieRepository: MovieRepository
    private var movie = MutableLiveData<MovieEntity>()

    init {
        movieRepository = MovieRepository(application)
    }

    fun getMovie(): MutableLiveData<MovieEntity> {
        return movie
    }

    fun getMovieDetail(movieID: Long): MutableLiveData<MovieEntity> {
        movie.postValue(movieRepository.getDetailMovie(movieID))
        return movie
    }

    fun setMovieFavorite(movieID: Long): MutableLiveData<MovieEntity> {
        movieRepository.setFavorite(movieID)
        movie.postValue(movieRepository.getDetailMovie(movieID))
        return movie
    }

    fun setMovieUnfavorite(movieID: Long): MutableLiveData<MovieEntity> {
        movieRepository.setUnfavorite(movieID)
        movie.postValue(movieRepository.getDetailMovie(movieID))
        return movie
    }

}
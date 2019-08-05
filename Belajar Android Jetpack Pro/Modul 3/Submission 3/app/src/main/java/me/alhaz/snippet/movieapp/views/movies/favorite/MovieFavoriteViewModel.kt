package me.alhaz.snippet.movieapp.views.movies.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity

class MovieFavoriteViewModel(application: Application): ViewModel() {

    private var movieRepository: MovieRepository

    init {
        movieRepository = MovieRepository(application)
    }

    fun getMovieFavorites(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getFavoriteMovies()
    }

}
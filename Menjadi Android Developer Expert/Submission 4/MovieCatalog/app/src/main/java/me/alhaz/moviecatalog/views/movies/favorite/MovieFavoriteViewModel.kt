package me.alhaz.moviecatalog.views.movies.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.movies.MovieRepository
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity

class MovieFavoriteViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getMovieFavorites(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getFavoriteMovies()
    }

}

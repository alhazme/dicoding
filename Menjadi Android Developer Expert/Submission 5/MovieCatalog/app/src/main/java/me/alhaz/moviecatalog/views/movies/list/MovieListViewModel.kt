package me.alhaz.moviecatalog.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.movies.MovieRepository
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.valueobject.Resource
import me.alhaz.snippet.movieapp.models.Movie

class MovieListViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private var movies = MutableLiveData<ArrayList<Movie>>()

    fun getListMovie(): LiveData<ArrayList<Movie>> {
        if (movies.value == null) {
            movies = movieRepository.getListMovie()
        }
        return movies
    }

    fun searchMovie(title: String): LiveData<ArrayList<Movie>> {
        return movieRepository.searchMovie(title)
    }

}
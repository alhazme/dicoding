package me.alhaz.snippet.movieapp.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.valueobject.Resource

class MovieListViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>> {
        return movieRepository.getListMovie()
    }

}
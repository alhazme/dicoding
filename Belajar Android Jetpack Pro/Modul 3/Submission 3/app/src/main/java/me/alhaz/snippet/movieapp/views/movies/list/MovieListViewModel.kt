package me.alhaz.snippet.movieapp.views.movies.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import okhttp3.Dispatcher

class MovieListViewModel(application: Application): ViewModel() {

    private var movieRepository: MovieRepository

    init {
        movieRepository = MovieRepository(application)
        movieRepository.getListMovieFromServer()
    }

    fun getMovieList(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getListMovie()
    }

}
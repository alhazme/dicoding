package me.alhaz.moviecatalog.views.movies.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.moviecatalog.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.models.Movie



class MovieListViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private var movies = MutableLiveData<ArrayList<Movie>>()

    fun getListMovie(): LiveData<ArrayList<Movie>> {
        if (movies.value == null) {
            movies = movieRepository.getListMovie()
        }
        return movies
    }

}
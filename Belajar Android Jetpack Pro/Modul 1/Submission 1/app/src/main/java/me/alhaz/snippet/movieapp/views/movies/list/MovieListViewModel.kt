package me.alhaz.snippet.movieapp.views.movies.list

import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.models.Movie

class MovieListViewModel: ViewModel() {

    val movies = ArrayList<Movie>()

    fun getMovieList(): ArrayList<Movie> {
        movies.clear()
        movies.addAll(DataDummy.listMovies())
        return movies
    }

}
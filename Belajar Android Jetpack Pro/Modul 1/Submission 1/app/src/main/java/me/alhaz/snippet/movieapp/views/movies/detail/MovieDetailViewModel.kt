package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.models.Movie

class MovieDetailViewModel: ViewModel() {

    var movie: Movie? = null

    fun getMovieDetail(movieID: Int): Movie? {
        val movies = DataDummy.listMovies()
        return movies.find {
            it.id == movieID
        }
    }

}
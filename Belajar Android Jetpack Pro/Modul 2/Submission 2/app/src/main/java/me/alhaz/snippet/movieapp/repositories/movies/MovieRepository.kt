package me.alhaz.snippet.movieapp.repositories.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.alhaz.snippet.movieapp.repositories.movies.local.MovieLocalRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.remote.MovieRemoteRepository
import me.alhaz.snippet.movieapp.repositories.movies.remote.response.MoviePopularResponse

class MovieRepository: MovieDataSource {

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MovieRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
                instance ?: MovieRepository().also {
                    instance = it
                }
        }

    }

    var movieRemoteRepository: MovieRemoteRepository? = null
    var movieLocalRepository: MovieLocalRepository? = null

    private var movies = MutableLiveData<ArrayList<Movie>>()
    private var movie = MutableLiveData<Movie>()

    init {
        movieRemoteRepository = MovieRemoteRepository()
        movieLocalRepository = MovieLocalRepository()
    }

    override fun getListMovie(): MutableLiveData<ArrayList<Movie>> {
        movieRemoteRepository?.let {
            movies = it.getListMovie()
        }
        return movies
    }

    override fun getDetailMovie(movieID: Long): MutableLiveData<Movie> {
        movieRemoteRepository?.let {
            movie = it.getDetailMovie(movieID)
        }
        return movie
    }

}
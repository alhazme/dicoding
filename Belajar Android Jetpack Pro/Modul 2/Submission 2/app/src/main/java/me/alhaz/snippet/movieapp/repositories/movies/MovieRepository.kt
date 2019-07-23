package me.alhaz.snippet.movieapp.repositories.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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

    override fun getListMovie(): MutableLiveData<ArrayList<Movie>> {
        val movies = MovieRemoteRepository().getListMovie()
        return movies
    }

    override fun getDetailMovie(movieID: Long): MutableLiveData<Movie> {
        val movie = MovieRemoteRepository().getDetailMovie(movieID)
        return movie
    }

}
package me.alhaz.moviecatalog.repositories.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.moviecatalog.repositories.movies.remote.MovieRemoteRepository
import me.alhaz.snippet.movieapp.models.Movie

class MovieRepository(private val movieRemoteRepository: MovieRemoteRepository): MovieDataSource {

    override fun getListMovie(): MutableLiveData<ArrayList<Movie>> {
        return movieRemoteRepository.getListMovie()
    }

    override fun getDetailMovie(movieID: Long): MutableLiveData<Movie> {
        return movieRemoteRepository.getDetailMovie(movieID)
    }

}
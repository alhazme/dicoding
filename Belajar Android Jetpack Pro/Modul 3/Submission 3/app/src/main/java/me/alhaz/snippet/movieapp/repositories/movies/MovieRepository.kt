package me.alhaz.snippet.movieapp.repositories.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.alhaz.snippet.movieapp.repositories.movies.local.MovieLocalRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.repositories.movies.remote.MovieRemoteRepository
import me.alhaz.snippet.movieapp.repositories.movies.remote.response.MoviePopularResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(application: Application): MovieDataSource {

    private var movieRemoteRepository: MovieRemoteRepository
    private var movieLocalRepository: MovieLocalRepository

    init {
        movieRemoteRepository = MovieRemoteRepository()
        movieLocalRepository = MovieLocalRepository(application)
        getListMovieFromServer()
    }

    override fun getListMovieFromServer() {
        movieRemoteRepository.let { remoteRepository ->
            val listMovie = remoteRepository.getListMovie()
            movieLocalRepository.let { localRepository ->
                listMovie.value?.let { movies ->
                    movies.forEach { movie ->
                        val movieEntity = MovieEntity(
                            id = movie.id,
                            title = movie.title,
                            voteAverage = movie.voteAverage,
                            overview = movie.overview,
                            releaseDate = movie.releaseDate,
                            runtime = movie.runtime,
                            posterPath = movie.posterPath
                        )
                        localRepository.insert(movieEntity)
                    }
                }
            }
        }
    }

    override fun getListMovie(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(movieLocalRepository.getMovieList(), 10).build()
    }

    override fun getDetailMovie(movieID: Long): MovieEntity {
        return movieLocalRepository.find(movieID)
    }
    override fun setFavorite(movieID: Long) {
        movieLocalRepository.setFavorite(movieID)
    }

    override fun setUnfavorite(movieID: Long) {
        movieLocalRepository.setUnfavorite(movieID)
    }

}
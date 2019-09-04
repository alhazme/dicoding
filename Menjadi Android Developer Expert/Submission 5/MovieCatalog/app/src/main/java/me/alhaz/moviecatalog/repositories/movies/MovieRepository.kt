package me.alhaz.moviecatalog.repositories.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.alhaz.moviecatalog.helper.AppExecutors
import me.alhaz.moviecatalog.repositories.NetworkBoundResource
import me.alhaz.moviecatalog.repositories.movies.local.MovieLocalRepository
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.repositories.movies.remote.MovieRemoteRepository
import me.alhaz.moviecatalog.valueobject.ApiResponse
import me.alhaz.moviecatalog.valueobject.Resource
import me.alhaz.snippet.movieapp.models.Movie

class MovieRepository(private val appExecutors: AppExecutors, private val movieRemoteRepository: MovieRemoteRepository, private val movieLocalRepository: MovieLocalRepository): MovieDataSource {

    override fun getListMovie(): MutableLiveData<ArrayList<Movie>> {
        return movieRemoteRepository.getListMovie()
    }

    override fun getDetailMovie(movieID: Long): MutableLiveData<Movie> {
        return movieRemoteRepository.getDetailMovie(movieID)
    }

    override fun isMovieFavorite(movieID: Long): MovieEntity {
        return movieLocalRepository.find(movieID)
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(movieLocalRepository.getMovieFavorites(), 10).build()
    }

    override fun setFavorite(movie: Movie) : MovieEntity {
        return movieLocalRepository.setFavorite(movie)
    }

    override fun setUnfavorite(movie: Movie) : MovieEntity {
        return movieLocalRepository.setUnfavorite(movie)
    }

    override fun searchMovie(title: String): LiveData<ArrayList<Movie>> {
        return movieRemoteRepository.searchMovie(title)
    }



}
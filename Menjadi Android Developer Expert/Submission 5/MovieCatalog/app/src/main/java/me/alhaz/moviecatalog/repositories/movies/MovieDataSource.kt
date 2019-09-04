package me.alhaz.moviecatalog.repositories.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.valueobject.Resource
import me.alhaz.snippet.movieapp.models.Movie

interface MovieDataSource {

    fun getListMovie(): MutableLiveData<ArrayList<Movie>>

    fun getDetailMovie(movieID: Long): LiveData<Movie>

    fun isMovieFavorite(movieID: Long): MovieEntity

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavorite(movie: Movie) : MovieEntity

    fun setUnfavorite(movie: Movie) : MovieEntity

    fun searchMovie(title: String): LiveData<ArrayList<Movie>>

}

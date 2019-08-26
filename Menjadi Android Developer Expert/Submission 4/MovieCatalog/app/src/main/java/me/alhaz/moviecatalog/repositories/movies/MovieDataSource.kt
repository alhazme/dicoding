package me.alhaz.moviecatalog.repositories.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.valueobject.Resource
import me.alhaz.snippet.movieapp.models.Movie

interface MovieDataSource {

    fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieID: Long): MovieEntity

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavorite(movieID: Long) : MovieEntity

    fun setUnfavorite(movieID: Long) : MovieEntity

}

package me.alhaz.snippet.movieapp.repositories.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity

interface MovieDataSource {

    fun getListMovieFromServer(): MutableLiveData<ArrayList<Movie>>

    fun getListMovie(): LiveData<PagedList<MovieEntity>>

    fun getDetailMovie(movieID: Long): MovieEntity

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavorite(movieID: Long) : MovieEntity

    fun setUnfavorite(movieID: Long) : MovieEntity

}
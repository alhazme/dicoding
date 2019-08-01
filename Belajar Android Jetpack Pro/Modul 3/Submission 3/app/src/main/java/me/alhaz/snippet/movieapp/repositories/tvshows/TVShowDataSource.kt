package me.alhaz.snippet.movieapp.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity

interface TVShowDataSource {

    fun getListTVShowFromServer()

    fun getListTVShow(): LiveData<PagedList<TVShowEntity>>

    fun getDetailTVShow(tvShowID: Long): TVShowEntity

    fun setFavorite(movieID: Long)

    fun setUnfavorite(movieID: Long)

}
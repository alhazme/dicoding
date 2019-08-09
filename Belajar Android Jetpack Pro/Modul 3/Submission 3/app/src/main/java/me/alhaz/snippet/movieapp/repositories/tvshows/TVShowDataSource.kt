package me.alhaz.snippet.movieapp.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.valueobject.Resource

interface TVShowDataSource {

    fun getListTVShow(): LiveData<Resource<PagedList<TVShowEntity>>>

    fun getDetailTVShow(tvShowID: Long): TVShowEntity

    fun getFavoriteTVShow(): LiveData<PagedList<TVShowEntity>>

    fun setFavorite(movieID: Long) : TVShowEntity

    fun setUnfavorite(movieID: Long) : TVShowEntity

}
package me.alhaz.moviecatalog.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.valueobject.Resource

interface TVShowDataSource {

    fun getListTVShow(): MutableLiveData<ArrayList<TVShow>>

    fun getDetailTVShow(tvShowID: Long): LiveData<TVShow>

    fun isTVShowFavorite(tvShowID: Long): TVShowEntity

    fun getFavoriteTVShow(): LiveData<PagedList<TVShowEntity>>

    fun setFavorite(tvShow: TVShow) : TVShowEntity

    fun setUnfavorite(tvShow: TVShow) : TVShowEntity

    fun searchTVShow(name: String): LiveData<ArrayList<TVShow>>

}
package me.alhaz.moviecatalog.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.valueobject.Resource

interface TVShowDataSource {

    fun getListTVShow(): LiveData<Resource<PagedList<TVShowEntity>>>

    fun getDetailTVShow(tvShowID: Long): TVShowEntity

    fun getFavoriteTVShow(): LiveData<PagedList<TVShowEntity>>

    fun setFavorite(tvShowID: Long) : TVShowEntity

    fun setUnfavorite(tvShowID: Long) : TVShowEntity

}
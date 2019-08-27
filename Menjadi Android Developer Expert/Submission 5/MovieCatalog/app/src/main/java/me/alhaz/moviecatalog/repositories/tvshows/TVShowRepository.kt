package me.alhaz.moviecatalog.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.alhaz.moviecatalog.helper.AppExecutors
import me.alhaz.moviecatalog.repositories.NetworkBoundResource
import me.alhaz.moviecatalog.repositories.tvshows.local.TVShowLocalRepository
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.repositories.tvshows.remote.TVShowRemoteRepository
import me.alhaz.moviecatalog.valueobject.ApiResponse
import me.alhaz.moviecatalog.valueobject.Resource

class TVShowRepository(private val appExecutors: AppExecutors, private val tvShowRemoteRepository: TVShowRemoteRepository, private val tvShowLocalRepository: TVShowLocalRepository): TVShowDataSource {

    override fun getListTVShow(): MutableLiveData<ArrayList<TVShow>> {
        return tvShowRemoteRepository.getListTVShow()
    }

    override fun getDetailTVShow(tvShowID: Long): MutableLiveData<TVShow> {
        return tvShowRemoteRepository.getDetailTVShow(tvShowID)
    }

    override fun isTVShowFavorite(tvShowID: Long): TVShowEntity {
        return tvShowLocalRepository.find(tvShowID)
    }

    override fun getFavoriteTVShow(): LiveData<PagedList<TVShowEntity>> {
        return LivePagedListBuilder(tvShowLocalRepository.getTVShowFavorite(), 10).build()
    }

    override fun setFavorite(tvShow: TVShow) : TVShowEntity {
        return tvShowLocalRepository.setFavorite(tvShow)
    }

    override fun setUnfavorite(tvShow: TVShow) : TVShowEntity {
        return tvShowLocalRepository.setUnfavorite(tvShow)
    }

    override fun searchTVShow(name: String): LiveData<ArrayList<TVShow>> {
        return tvShowRemoteRepository.searchTVShow(name)
    }

}
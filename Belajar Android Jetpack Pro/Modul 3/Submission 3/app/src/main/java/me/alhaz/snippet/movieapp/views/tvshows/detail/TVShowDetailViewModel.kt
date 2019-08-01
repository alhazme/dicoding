package me.alhaz.snippet.movieapp.views.tvshows.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity

class TVShowDetailViewModel(application: Application): ViewModel() {

    private var tvShowRepository: TVShowRepository
    private var tvShow = MutableLiveData<TVShowEntity>()

    init {
        tvShowRepository = TVShowRepository(application)
    }

    fun getTVShow(): MutableLiveData<TVShowEntity> {
        return tvShow
    }

    fun getTVShowDetail(tvShowID: Long): MutableLiveData<TVShowEntity> {
        tvShow.postValue(tvShowRepository.getDetailTVShow(tvShowID))
        return tvShow
    }

    fun setTVShowFavorite(tvShowID: Long): MutableLiveData<TVShowEntity> {
        tvShowRepository.setFavorite(tvShowID)
        tvShow.postValue(tvShowRepository.getDetailTVShow(tvShowID))
        return tvShow
    }

    fun setTVShowUnfavorite(tvShowID: Long): MutableLiveData<TVShowEntity> {
        tvShowRepository.setUnfavorite(tvShowID)
        tvShow.postValue(tvShowRepository.getDetailTVShow(tvShowID))
        return tvShow
    }

}
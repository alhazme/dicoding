package me.alhaz.snippet.movieapp.views.tvshows.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity

class TVShowDetailViewModel(private val tvShowRepository: TVShowRepository): ViewModel() {

    private var tvShow = MutableLiveData<TVShowEntity>()

    fun getTVShow(): MutableLiveData<TVShowEntity> {
        return tvShow
    }

    fun getTVShowDetail(tvShowID: Long): MutableLiveData<TVShowEntity> {
        tvShow.postValue(tvShowRepository.getDetailTVShow(tvShowID))
        return tvShow
    }

    fun setTVShowFavorite(tvShowID: Long): MutableLiveData<TVShowEntity> {
        tvShow.postValue(tvShowRepository.setFavorite(tvShowID))
        return tvShow
    }

    fun setTVShowUnfavorite(tvShowID: Long): MutableLiveData<TVShowEntity> {
        tvShow.postValue(tvShowRepository.setUnfavorite(tvShowID))
        return tvShow
    }

}
package me.alhaz.snippet.movieapp.views.tvshows.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity

class TVShowFavoriteViewModel(tvShowRepository: TVShowRepository): ViewModel() {

    var tvShowRepository: TVShowRepository

    init {
        this.tvShowRepository = tvShowRepository
    }

    fun getTVShowFavorite(): LiveData<PagedList<TVShowEntity>> {
        return tvShowRepository.getFavoriteTVShow()
    }
}
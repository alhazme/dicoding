package me.alhaz.snippet.movieapp.views.tvshows.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity

class TVShowFavoriteViewModel(application: Application): ViewModel() {

    private var tvShowRepository: TVShowRepository

    init {
        tvShowRepository = TVShowRepository(application)
    }

    fun getTVShowFavorite(): LiveData<PagedList<TVShowEntity>> {
        return tvShowRepository.getFavoriteTVShow()
    }
}
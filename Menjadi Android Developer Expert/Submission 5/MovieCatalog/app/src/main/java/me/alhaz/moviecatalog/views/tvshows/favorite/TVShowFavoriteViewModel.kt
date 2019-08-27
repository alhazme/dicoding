package me.alhaz.moviecatalog.views.tvshows.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.tvshows.TVShowRepository
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity

class TVShowFavoriteViewModel(private val tvShowRepository: TVShowRepository): ViewModel() {

    fun getTVShowFavorite(): LiveData<PagedList<TVShowEntity>> {
        return tvShowRepository.getFavoriteTVShow()
    }
}
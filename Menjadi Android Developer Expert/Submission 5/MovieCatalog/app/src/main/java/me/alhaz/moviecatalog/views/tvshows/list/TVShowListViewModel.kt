package me.alhaz.moviecatalog.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.tvshows.TVShowRepository
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.valueobject.Resource

class TVShowListViewModel (private val tvShowRepository: TVShowRepository): ViewModel() {

    private var tvShows = MutableLiveData<ArrayList<TVShow>>()

    fun getTVShowList(): LiveData<ArrayList<TVShow>> {
        if (tvShows.value == null) {
            tvShows = tvShowRepository.getListTVShow()
        }
        return tvShows
    }

    fun searchTVShow(title: String): LiveData<ArrayList<TVShow>> {
        return tvShowRepository.searchTVShow(title)
    }
}

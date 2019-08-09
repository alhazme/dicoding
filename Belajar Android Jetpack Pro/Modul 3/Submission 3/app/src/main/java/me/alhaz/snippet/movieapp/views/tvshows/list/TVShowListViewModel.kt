package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.valueobject.Resource

class TVShowListViewModel(private val tvShowRepository: TVShowRepository): ViewModel() {

    fun getTVShowList(): LiveData<Resource<PagedList<TVShowEntity>>> {
        return tvShowRepository.getListTVShow()
    }
}
package me.alhaz.moviecatalog.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.moviecatalog.repositories.tvshows.TVShowRepository
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.valueobject.Resource

class TVShowListViewModel (private val tvShowRepository: TVShowRepository): ViewModel() {

    fun getTVShowList(): LiveData<Resource<PagedList<TVShowEntity>>> {
        return tvShowRepository.getListTVShow()
    }
}

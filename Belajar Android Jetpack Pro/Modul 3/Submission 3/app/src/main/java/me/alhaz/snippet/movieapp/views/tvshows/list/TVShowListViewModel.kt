package me.alhaz.snippet.movieapp.views.tvshows.list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity

class TVShowListViewModel(application: Application): ViewModel() {

    private var tvShowRepository: TVShowRepository

    init {
        tvShowRepository = TVShowRepository(application)
    }

    fun getTVShowList(): LiveData<PagedList<TVShowEntity>> {
        return tvShowRepository.getListTVShow()
    }
}
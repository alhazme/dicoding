package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow

class TVShowListViewModel: ViewModel() {

    var tvShowRepository: TVShowRepository? = null
    private var tvShows = MutableLiveData<ArrayList<TVShow>>()

    init {
        tvShowRepository = TVShowRepository()
    }

    fun getTVShows(): MutableLiveData<ArrayList<TVShow>> {
        return tvShows
    }

    fun getTVShowList(): MutableLiveData<ArrayList<TVShow>> {
        tvShowRepository?.let {
            tvShows = it.getListTVShow()
        }
        return tvShows
    }
}
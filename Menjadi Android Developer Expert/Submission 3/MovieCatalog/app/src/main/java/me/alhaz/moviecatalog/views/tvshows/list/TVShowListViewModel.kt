package me.alhaz.moviecatalog.views.tvshows.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.moviecatalog.model.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.TVShowRepository

class TVShowListViewModel (private val tvShowRepository: TVShowRepository): ViewModel() {

    private var tvShows = MutableLiveData<ArrayList<TVShow>>()

    fun getTVShowList(): LiveData<ArrayList<TVShow>> {
        if (tvShows.value == null) {
            tvShows = tvShowRepository.getListTVShow()
        }
        return tvShows
    }
}

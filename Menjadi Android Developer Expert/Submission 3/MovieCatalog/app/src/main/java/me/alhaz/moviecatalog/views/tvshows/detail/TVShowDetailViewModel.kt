package me.alhaz.moviecatalog.views.tvshows.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.moviecatalog.model.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.TVShowRepository

class TVShowDetailViewModel(private val tvShowRepository: TVShowRepository): ViewModel() {

    private var tvShow = MutableLiveData<TVShow>()

    fun getTVShowDetail(tvShowID: Long): LiveData<TVShow> {
        if (tvShow.value == null) {
            tvShow = tvShowRepository.getDetailTVShow(tvShowID)
        }
        return tvShow
    }

}
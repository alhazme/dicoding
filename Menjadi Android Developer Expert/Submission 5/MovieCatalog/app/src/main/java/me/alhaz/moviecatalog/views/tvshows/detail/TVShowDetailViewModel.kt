package me.alhaz.moviecatalog.views.tvshows.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.TVShowRepository
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity

class TVShowDetailViewModel(private val tvShowRepository: TVShowRepository): ViewModel() {

    private var tvShow = MutableLiveData<TVShow>()

    fun getTVShowDetail(tvShowID: Long): LiveData<TVShow> {
        if (tvShow.value == null) {
            tvShow = tvShowRepository.getDetailTVShow(tvShowID)
        }
        return tvShow
    }

    fun isTVShowFavorite(movieID: Long): TVShowEntity {
        return tvShowRepository.isTVShowFavorite(movieID)
    }

    fun setTVShowFavorite(tvShow: TVShow): TVShowEntity {
        return tvShowRepository.setFavorite(tvShow)
    }

    fun setTVShowUnfavorite(tvShow: TVShow): TVShowEntity {
        return tvShowRepository.setUnfavorite(tvShow)
    }

}

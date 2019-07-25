package me.alhaz.snippet.movieapp.views.tvshows.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow

class TVShowDetailViewModel: ViewModel() {

    var tvShowRepository: TVShowRepository? = null
    private var tvShow = MutableLiveData<TVShow>()

    init {
        tvShowRepository = TVShowRepository()
    }

    fun getTVShow(): MutableLiveData<TVShow> {
        return tvShow
    }

    fun getTVShowDetail(tvShowID: Long): MutableLiveData<TVShow>  {
        tvShowRepository?.let {
            tvShow = it.getDetailTVShow(tvShowID)
        }
        return tvShow
    }

}
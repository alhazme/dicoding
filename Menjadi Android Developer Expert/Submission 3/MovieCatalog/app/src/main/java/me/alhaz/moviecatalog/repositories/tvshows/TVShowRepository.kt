package me.alhaz.moviecatalog.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.moviecatalog.model.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.remote.TVShowRemoteRepository

class TVShowRepository(private val tvShowRemoteRepository: TVShowRemoteRepository): TVShowDataSource {

    override fun getListTVShow(): MutableLiveData<ArrayList<TVShow>> {
        return tvShowRemoteRepository.getListTVShow()
    }

    override fun getDetailTVShow(tvShowID: Long): MutableLiveData<TVShow> {
        return tvShowRemoteRepository.getDetailTVShow(tvShowID)
    }

}
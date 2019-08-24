package me.alhaz.moviecatalog.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.moviecatalog.model.TVShow

interface TVShowDataSource {

    fun getListTVShow(): MutableLiveData<ArrayList<TVShow>>

    fun getDetailTVShow(tvShowID: Long): MutableLiveData<TVShow>
}
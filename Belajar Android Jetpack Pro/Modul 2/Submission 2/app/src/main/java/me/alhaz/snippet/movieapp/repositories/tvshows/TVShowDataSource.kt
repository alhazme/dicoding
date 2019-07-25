package me.alhaz.snippet.movieapp.repositories.tvshows

import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow

interface TVShowDataSource {

    fun getListTVShow(): MutableLiveData<ArrayList<TVShow>>

    fun getDetailTVShow(tvShowID: Long): MutableLiveData<TVShow>

}
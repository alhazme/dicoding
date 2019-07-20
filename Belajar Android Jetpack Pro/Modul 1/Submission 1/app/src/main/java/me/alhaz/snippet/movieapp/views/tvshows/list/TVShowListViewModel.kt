package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.models.TVShow

class TVShowListViewModel: ViewModel() {

    val tvShows = ArrayList<TVShow>()

    fun getTVShowList(): ArrayList<TVShow> {
        tvShows.clear()
        tvShows.addAll(DataDummy.listTVShows())
        return tvShows
    }
}
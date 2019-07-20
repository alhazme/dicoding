package me.alhaz.snippet.movieapp.views.tvshows.detail

import androidx.lifecycle.ViewModel
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.models.TVShow

class TVShowDetailViewModel: ViewModel() {

    var tvShow: TVShow? = null

    fun getTVShowDetail(tvShowID: Int): TVShow? {
        val tvShows = DataDummy.listTVShows()
        return tvShows.find {
            it.id == tvShowID
        }
    }

}
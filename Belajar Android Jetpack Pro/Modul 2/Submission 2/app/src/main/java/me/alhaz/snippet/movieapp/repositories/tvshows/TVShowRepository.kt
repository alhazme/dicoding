package me.alhaz.snippet.movieapp.repositories.tvshows

import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.TVShowRemoteRepository

class TVShowRepository: TVShowDataSource {

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: TVShowRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: TVShowRepository().also {
                instance = it
            }
        }

    }
    override fun getListTVShow(): MutableLiveData<ArrayList<TVShow>> {
        val tvShows = TVShowRemoteRepository().getListTVShow()
        return tvShows
    }

    override fun getDetailTVShow(tvShowID: Long): MutableLiveData<TVShow> {
        val tvShow = TVShowRemoteRepository().getDetailTVShow(tvShowID)
        return tvShow
    }

}
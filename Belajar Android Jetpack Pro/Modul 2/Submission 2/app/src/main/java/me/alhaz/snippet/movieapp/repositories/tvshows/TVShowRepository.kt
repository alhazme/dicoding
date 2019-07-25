package me.alhaz.snippet.movieapp.repositories.tvshows

import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.tvshows.local.TVShowLocalRepository
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

    var tvShowRemoteRepository: TVShowRemoteRepository? = null
    var tvShowLocalRepository: TVShowLocalRepository? = null

    private var tvShows = MutableLiveData<ArrayList<TVShow>>()
    private var tvShow = MutableLiveData<TVShow>()

    init {
        tvShowRemoteRepository = TVShowRemoteRepository()
        tvShowLocalRepository = TVShowLocalRepository()
    }

    override fun getListTVShow(): MutableLiveData<ArrayList<TVShow>> {
        tvShowRemoteRepository?.let {
            tvShows = it.getListTVShow()
        }
        return tvShows
    }

    override fun getDetailTVShow(tvShowID: Long): MutableLiveData<TVShow> {
        tvShowRemoteRepository?.let {
            tvShow = it.getDetailTVShow(tvShowID)
        }
        return tvShow
    }

}
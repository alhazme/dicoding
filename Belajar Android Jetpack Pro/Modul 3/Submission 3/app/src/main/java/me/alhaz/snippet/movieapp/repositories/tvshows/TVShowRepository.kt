package me.alhaz.snippet.movieapp.repositories.tvshows

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.tvshows.local.TVShowLocalRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.TVShowRemoteRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.response.TVShowPopularResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowRepository(application: Application): TVShowDataSource {

    private var tvShowRemoteRepository: TVShowRemoteRepository
    private var tvShowLocalRepository: TVShowLocalRepository

    init {
        tvShowRemoteRepository = TVShowRemoteRepository()
        tvShowLocalRepository = TVShowLocalRepository(application)
        getListTVShowFromServer()
    }

    override fun getListTVShowFromServer() {
        tvShowRemoteRepository.let { remoteRepository ->
            val listTVShow = remoteRepository.getListTVShow()
            tvShowLocalRepository.let { localRepository ->
                listTVShow.value?.let { tvShows ->
                    tvShows.forEach { tvShow ->
                        val tvShowEntity = TVShowEntity(
                            id = tvShow.id,
                            name = tvShow.name,
                            voteAverage = tvShow.voteAverage,
                            overview = tvShow.overview,
                            firstAirDate = tvShow.firstAirDate,
                            numberOfEpisodes = tvShow.numberOfEpisodes,
                            posterPath = tvShow.posterPath
                        )
                        localRepository.insert(tvShowEntity)
                    }
                }
            }
        }
    }

    override fun getListTVShow(): LiveData<PagedList<TVShowEntity>> {
        return LivePagedListBuilder(tvShowLocalRepository.getTVShowList(), 10).build()
    }

    override fun getDetailTVShow(tvShowID: Long): TVShowEntity {
        return tvShowLocalRepository.find(tvShowID)
    }

    override fun getFavoriteTVShow(): LiveData<PagedList<TVShowEntity>> {
        return LivePagedListBuilder(tvShowLocalRepository.getTVShowFavorite(), 10).build()
    }

    override fun setFavorite(tvShowID: Long) {
        tvShowLocalRepository.setFavorite(tvShowID)
    }

    override fun setUnfavorite(tvShowID: Long) {
        tvShowLocalRepository.setUnfavorite(tvShowID)
    }

}
package me.alhaz.snippet.movieapp.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.tvshows.local.TVShowLocalRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.TVShowRemoteRepository

class TVShowRepository(tvShowRemoteRepository: TVShowRemoteRepository, tvShowLocalRepository: TVShowLocalRepository): TVShowDataSource {

    var tvShowRemoteRepository: TVShowRemoteRepository
    var tvShowLocalRepository: TVShowLocalRepository

    init {
        this.tvShowRemoteRepository = tvShowRemoteRepository
        this.tvShowLocalRepository = tvShowLocalRepository
    }

    override fun getListTVShowFromServer(): MutableLiveData<ArrayList<TVShow>> {
        val tvShowLiveData = MutableLiveData<ArrayList<TVShow>>()
        tvShowRemoteRepository.let { remoteRepository ->
            val listTVShow = remoteRepository.getListTVShow()
            tvShowLocalRepository.let { localRepository ->
                listTVShow.value?.let { tvShows ->
                    tvShowLiveData.value = tvShows
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
        return tvShowLiveData
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

    override fun setFavorite(tvShowID: Long) : TVShowEntity {
        return tvShowLocalRepository.setFavorite(tvShowID)
    }

    override fun setUnfavorite(tvShowID: Long) : TVShowEntity {
        return tvShowLocalRepository.setUnfavorite(tvShowID)
    }

}
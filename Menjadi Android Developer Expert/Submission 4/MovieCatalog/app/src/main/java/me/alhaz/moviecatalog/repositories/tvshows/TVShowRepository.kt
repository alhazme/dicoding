package me.alhaz.moviecatalog.repositories.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.alhaz.moviecatalog.helper.AppExecutors
import me.alhaz.moviecatalog.repositories.NetworkBoundResource
import me.alhaz.moviecatalog.repositories.tvshows.local.TVShowLocalRepository
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.repositories.tvshows.remote.TVShowRemoteRepository
import me.alhaz.moviecatalog.valueobject.ApiResponse
import me.alhaz.moviecatalog.valueobject.Resource

class TVShowRepository(private val appExecutors: AppExecutors, private val tvShowRemoteRepository: TVShowRemoteRepository, private val tvShowLocalRepository: TVShowLocalRepository): TVShowDataSource {

    override fun getListTVShow(): LiveData<Resource<PagedList<TVShowEntity>>> {

        return object: NetworkBoundResource<PagedList<TVShowEntity>, ArrayList<TVShow>>(appExecutors) {

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean {
                return (tvShowLocalRepository.counts() == 0)
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<TVShow>>> {
                return tvShowRemoteRepository.getListTVShow()
            }

            override fun saveCallResult(item: ArrayList<TVShow>) {
                item.forEach { tvShow ->
                    val tvShowEntity = TVShowEntity(
                        id = tvShow.id,
                        name = tvShow.name,
                        voteAverage = tvShow.voteAverage,
                        overview = tvShow.overview,
                        overview_id = tvShow.overview_id,
                        firstAirDate = tvShow.firstAirDate,
                        numberOfEpisodes = tvShow.numberOfEpisodes,
                        posterPath = tvShow.posterPath
                    )
                    tvShowLocalRepository.insert(tvShowEntity)
                }
            }

            override fun loadFromDb(): LiveData<PagedList<TVShowEntity>> {
                return LivePagedListBuilder(tvShowLocalRepository.getTVShowList(), 10).build()
            }

        }.asLiveData()
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
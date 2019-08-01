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
    }

    override fun getListTVShowFromServer() {
        tvShowRemoteRepository.let { remoteRepository ->
            remoteRepository.getListTVShow(object: Callback<TVShowPopularResponse> {
                override fun onResponse(call: Call<TVShowPopularResponse>, response: Response<TVShowPopularResponse>) {
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        responseData?.let { tvShowPopularResponse ->
                            tvShowPopularResponse.results?.let { tvShows ->
                                if (tvShows.isNotEmpty()) {
                                    tvShowLocalRepository.deleteAll()
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
                                        tvShowLocalRepository?.insert(tvShowEntity)
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<TVShowPopularResponse>, t: Throwable) {

                }
            })
        }
    }

    override fun getListTVShow(): LiveData<PagedList<TVShowEntity>> {
        return LivePagedListBuilder(tvShowLocalRepository.getTVShowList(), 10).build()
    }

    override fun getDetailTVShow(tvShowID: Long): TVShowEntity {
        return tvShowLocalRepository.find(tvShowID)
    }
    override fun setFavorite(tvShowID: Long) {
        tvShowLocalRepository.setFavorite(tvShowID)
    }

    override fun setUnfavorite(tvShowID: Long) {
        tvShowLocalRepository.setUnfavorite(tvShowID)
    }

}
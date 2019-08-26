package me.alhaz.moviecatalog.repositories.tvshows.remote

import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.remote.response.TVShowPopularResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowAPIService {

    @GET("tvshow/popular.json")
    fun getListTVShow(
        @Query("api_key") api_key: String
    ): Call<TVShowPopularResponse>

    @GET("tvshow/{tv_id}.json")
    fun getDetailTVShow(
        @Path("tv_id") tv_id: Long,
        @Query("api_key") api_key: String
    ): Call<TVShow>
}
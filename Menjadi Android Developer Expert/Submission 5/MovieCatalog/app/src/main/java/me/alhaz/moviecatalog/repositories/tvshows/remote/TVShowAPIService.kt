package me.alhaz.moviecatalog.repositories.tvshows.remote

import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.remote.response.TVShowPopularResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowAPIService {

    @GET("discover/tv")
    fun getListTVShow(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<TVShowPopularResponse>

    @GET("search/tv")
    fun searchTVShow(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<TVShowPopularResponse>


    @GET("tv/{tv_id}")
    fun getDetailTVShow(
        @Path("tv_id") tv_id: Long,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<TVShow>
}
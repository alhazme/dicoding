package me.alhaz.snippet.movieapp.repositories.tvshows.remote

import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.remote.response.MoviePopularResponse
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.response.TVShowPopularResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowAPIService {

    @GET("tv/popular")
    fun getListTVShow(
        @Query("api_key") api_key: String
    ): Call<TVShowPopularResponse>

    @GET("tv/{tv_id}")
    fun getDetailTVShow(
        @Path("tv_id") tv_id: Long,
        @Query("api_key") api_key: String
    ): Call<TVShow>
}
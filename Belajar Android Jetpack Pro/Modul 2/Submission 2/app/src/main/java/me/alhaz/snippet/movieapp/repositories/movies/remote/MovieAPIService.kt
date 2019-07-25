package me.alhaz.snippet.movieapp.repositories.movies.remote

import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.remote.response.MoviePopularResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService {

    @GET("movie/popular")
    fun getListMovie(
        @Query("api_key") api_key: String
    ): Call<MoviePopularResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") api_key: String
    ): Call<Movie>

//    @GET("tv/popular")
//    fun getTVShowList(@Query("api_key") id: String): Call<TVShowPopularResponse>
}
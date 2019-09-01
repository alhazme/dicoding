package me.alhaz.moviecatalog.repositories.movies.remote

import me.alhaz.moviecatalog.repositories.movies.remote.response.MoviePopularResponse
import me.alhaz.snippet.movieapp.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService {

    @GET("discover/movie")
    fun getListMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<MoviePopularResponse>

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<MoviePopularResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<Movie>

    @GET("discover/movie")
    fun getTodayReleaseMovie(
        @Query("api_key") api_key: String,
        @Query("primary_release_date.gte") primary_release_date_gte: String,
        @Query("primary_release_date.lte") primary_release_date_lte: String
    ): Call<MoviePopularResponse>
}
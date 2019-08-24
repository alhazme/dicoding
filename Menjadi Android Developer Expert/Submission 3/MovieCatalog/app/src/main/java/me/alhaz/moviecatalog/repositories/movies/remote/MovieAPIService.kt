package me.alhaz.moviecatalog.repositories.movies.remote

import me.alhaz.moviecatalog.repositories.movies.remote.response.MoviePopularResponse
import me.alhaz.snippet.movieapp.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService {

    @GET("movie/popular.json")
    fun getListMovie(
        @Query("api_key") api_key: String
    ): Call<MoviePopularResponse>

    @GET("movie/{movie_id}.json")
    fun getDetailMovie(
        @Path("movie_id") movie_id: Long,
        @Query("api_key") api_key: String
    ): Call<Movie>
}
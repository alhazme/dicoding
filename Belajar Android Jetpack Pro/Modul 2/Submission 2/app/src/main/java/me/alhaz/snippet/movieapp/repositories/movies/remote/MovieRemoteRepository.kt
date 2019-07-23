package me.alhaz.snippet.movieapp.repositories.movies.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import me.alhaz.snippet.movieapp.BuildConfig
import me.alhaz.snippet.movieapp.helper.RetrofitConfig
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.remote.response.MoviePopularResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class MovieRemoteRepository {

    fun getListMovie(): MutableLiveData<ArrayList<Movie>> {

        val movies = MutableLiveData<ArrayList<Movie>>()

        RetrofitConfig().getMovieService().getListMovie(BuildConfig.API_KEY).enqueue(object: Callback<MoviePopularResponse> {

            override fun onResponse(call: Call<MoviePopularResponse>, response: Response<MoviePopularResponse>) {
                if (response.isSuccessful) {
                    val movieResponses = ArrayList<Movie>()
                    val responseData = response.body()
                    responseData?.let {
                        val moviePopularResponse: MoviePopularResponse = it
                        moviePopularResponse.results?.let {
                            val data: List<Movie> = it
                            if (data.isNotEmpty()) {
                                data.forEach {
                                    movieResponses.add(it)
                                }
                            }
                        }
                    }
                    Log.d("1234567890", movieResponses.toString())
                    movies.postValue(movieResponses)
                }
            }

            override fun onFailure(call: Call<MoviePopularResponse>, t: Throwable) {

            }
        })

        return movies
    }

    fun getDetailMovie(movieID: Long): MutableLiveData<Movie> {

        var movie = MutableLiveData<Movie>()

        RetrofitConfig().getMovieService().getDetailMovie(movieID, BuildConfig.API_KEY).enqueue(object: Callback<Movie> {

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let {
                        movie.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return movie
    }

}
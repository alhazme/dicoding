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

    fun getListMovie(callback: Callback<MoviePopularResponse>) {

        RetrofitConfig().getMovieService().getListMovie(BuildConfig.API_KEY).enqueue(callback)
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
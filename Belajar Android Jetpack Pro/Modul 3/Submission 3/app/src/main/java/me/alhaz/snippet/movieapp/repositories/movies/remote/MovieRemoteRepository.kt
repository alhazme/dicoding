package me.alhaz.snippet.movieapp.repositories.movies.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.BuildConfig
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.helper.RetrofitConfig
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.remote.response.MoviePopularResponse
import me.alhaz.snippet.movieapp.valueobject.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteRepository {

    fun getListMovie() : LiveData<ApiResponse<ArrayList<Movie>>> {
        EspressoIdlingResource.increment()
        val moviesLiveData = MutableLiveData<ApiResponse<ArrayList<Movie>>>()
        RetrofitConfig().getMovieService().getListMovie(BuildConfig.API_KEY).enqueue(object: Callback<MoviePopularResponse> {
            override fun onResponse(call: Call<MoviePopularResponse>, response: Response<MoviePopularResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { moviePopularResponse ->
                        moviePopularResponse.results?.let { moviesResult ->
                            val movies = ArrayList<Movie>()
                            movies.addAll(moviesResult)
                            moviesLiveData.postValue(ApiResponse.success(movies))
                        }
                    }
                    if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow()) {
                        EspressoIdlingResource.decrement()
                    }
                }
                else {
                    if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow()) {
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<MoviePopularResponse>, t: Throwable) {
                moviesLiveData.postValue(null)
                if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow()) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
        return moviesLiveData
    }

}
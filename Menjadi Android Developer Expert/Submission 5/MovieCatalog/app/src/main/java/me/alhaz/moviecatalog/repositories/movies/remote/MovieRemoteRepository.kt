package me.alhaz.moviecatalog.repositories.movies.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.moviecatalog.BuildConfig
import me.alhaz.moviecatalog.helper.EspressoIdlingResource
import me.alhaz.moviecatalog.helper.RetrofitConfig
import me.alhaz.moviecatalog.repositories.movies.remote.response.MoviePopularResponse
import me.alhaz.moviecatalog.valueobject.ApiResponse
import me.alhaz.snippet.movieapp.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteRepository {

    fun getListMovie() : MutableLiveData<ArrayList<Movie>> {
        EspressoIdlingResource.increment()
        val moviesLiveData = MutableLiveData<ArrayList<Movie>>()
        RetrofitConfig().getMovieService().getListMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE).enqueue(object: Callback<MoviePopularResponse> {
            override fun onResponse(call: Call<MoviePopularResponse>, response: Response<MoviePopularResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { moviePopularResponse ->
                        moviePopularResponse.results?.let { moviesResult ->
                            val movies = ArrayList<Movie>()
                            movies.addAll(moviesResult)
                            moviesLiveData.postValue(movies)
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

    fun getDetailMovie(movieID: Long) : MutableLiveData<Movie> {
        EspressoIdlingResource.increment()
        val movieLiveData = MutableLiveData<Movie>()
        RetrofitConfig().getMovieService().getDetailMovie(movieID, BuildConfig.API_KEY, BuildConfig.LANGUAGE).enqueue(object: Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { movie ->
                        movieLiveData.postValue(movie)
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

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieLiveData.postValue(null)
                if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow()) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
        return movieLiveData
    }

    fun searchMovie(title: String) : LiveData<ArrayList<Movie>> {
        EspressoIdlingResource.increment()
        val moviesLiveData = MutableLiveData<ArrayList<Movie>>()
        RetrofitConfig().getMovieService().searchMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE, title).enqueue(object: Callback<MoviePopularResponse> {
            override fun onResponse(call: Call<MoviePopularResponse>, response: Response<MoviePopularResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { moviePopularResponse ->
                        moviePopularResponse.results?.let { moviesResult ->
                            val movies = ArrayList<Movie>()
                            movies.addAll(moviesResult)
                            moviesLiveData.postValue(movies)
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

    fun getTodayReleaseMovie(date: String, callback: Callback<MoviePopularResponse>) {
        EspressoIdlingResource.increment()
        RetrofitConfig().getMovieService().getTodayReleaseMovie(BuildConfig.API_KEY, date, date).enqueue(callback)

    }

}
package me.alhaz.snippet.movieapp.repositories.tvshows.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.BuildConfig
import me.alhaz.snippet.movieapp.helper.RetrofitConfig
import me.alhaz.snippet.movieapp.repositories.movies.remote.response.MoviePopularResponse
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.response.TVShowPopularResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowRemoteRepository {

//    fun getListTVShow(callback: Callback<TVShowPopularResponse>) {
//        RetrofitConfig().getTVShowService().getListTVShow(BuildConfig.API_KEY).enqueue(callback)
//    }

    fun getListTVShow(): LiveData<ArrayList<TVShow>> {
        val tvShowsLiveData = MutableLiveData<ArrayList<TVShow>>()
        val tvShows = ArrayList<TVShow>()
        RetrofitConfig().getTVShowService().getListTVShow(BuildConfig.API_KEY).enqueue(object: Callback<TVShowPopularResponse> {
            override fun onResponse(call: Call<TVShowPopularResponse>, response: Response<TVShowPopularResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { tvShowPopularResponse ->
                        tvShowPopularResponse.results?.let { tvShowsResult ->
                            tvShows.addAll(tvShowsResult)
                            tvShowsLiveData.value = tvShows
                        }
                    }
                }
            }

            override fun onFailure(call: Call<TVShowPopularResponse>, t: Throwable) {

            }
        })
        return tvShowsLiveData
    }

    fun getDetailTVShow(tvShowID: Long): MutableLiveData<TVShow> {

        var tvShow = MutableLiveData<TVShow>()

        RetrofitConfig().getTVShowService().getDetailTVShow(tvShowID, BuildConfig.API_KEY).enqueue(object: Callback<TVShow> {

            override fun onResponse(call: Call<TVShow>, response: Response<TVShow>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let {
                        tvShow.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<TVShow>, t: Throwable) {

            }
        })

        return tvShow
    }
}
package me.alhaz.snippet.movieapp.repositories.tvshows.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.BuildConfig
import me.alhaz.snippet.movieapp.helper.RetrofitConfig
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.response.TVShowPopularResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowRemoteRepository {

    fun getListTVShow(): MutableLiveData<ArrayList<TVShow>> {

        val tvShows = MutableLiveData<ArrayList<TVShow>>()

        RetrofitConfig().getTVShowService().getListTVShow(BuildConfig.API_KEY).enqueue(object: Callback<TVShowPopularResponse> {

            override fun onResponse(call: Call<TVShowPopularResponse>, response: Response<TVShowPopularResponse>) {
                Log.d("1234567890", response.toString())
                if (response.isSuccessful) {
                    val tvShowResponses = ArrayList<TVShow>()
                    val responseData = response.body()
                    responseData?.let {
                        val tvShowPopularResponse: TVShowPopularResponse = it
                        tvShowPopularResponse.results?.let {
                            val data: List<TVShow> = it
                            if (data.isNotEmpty()) {
                                data.forEach {
                                    tvShowResponses.add(it)
                                }
                            }
                        }
                    }
                    Log.d("1234567890", tvShowResponses.toString())
                    tvShows.postValue(tvShowResponses)
                }
            }

            override fun onFailure(call: Call<TVShowPopularResponse>, t: Throwable) {

            }
        })

        return tvShows
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
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return tvShow
    }
}
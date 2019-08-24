package me.alhaz.moviecatalog.repositories.tvshows.remote

import androidx.lifecycle.MutableLiveData
import me.alhaz.moviecatalog.BuildConfig
import me.alhaz.moviecatalog.helper.RetrofitConfig
import me.alhaz.moviecatalog.model.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.remote.response.TVShowPopularResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowRemoteRepository {

    fun getListTVShow(): MutableLiveData<ArrayList<TVShow>> {

        val tvShows = MutableLiveData<ArrayList<TVShow>>()

        RetrofitConfig().getTVShowService().getListTVShow(BuildConfig.API_KEY).enqueue(object:
            Callback<TVShowPopularResponse> {

            override fun onResponse(call: Call<TVShowPopularResponse>, response: Response<TVShowPopularResponse>) {
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
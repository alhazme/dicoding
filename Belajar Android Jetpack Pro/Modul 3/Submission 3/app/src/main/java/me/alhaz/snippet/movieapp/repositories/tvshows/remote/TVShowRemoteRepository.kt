package me.alhaz.snippet.movieapp.repositories.tvshows.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.BuildConfig
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.helper.RetrofitConfig
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.response.TVShowPopularResponse
import me.alhaz.snippet.movieapp.valueobject.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowRemoteRepository {

    fun getListTVShow(): LiveData<ApiResponse<ArrayList<TVShow>>> {
        EspressoIdlingResource.increment()
        val tvShowsLiveData = MutableLiveData<ApiResponse<ArrayList<TVShow>>>()
        RetrofitConfig().getTVShowService().getListTVShow(BuildConfig.API_KEY).enqueue(object: Callback<TVShowPopularResponse> {
            override fun onResponse(call: Call<TVShowPopularResponse>, response: Response<TVShowPopularResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { tvShowPopularResponse ->
                        tvShowPopularResponse.results?.let { tvShowsResult ->
                            val tvShows = ArrayList<TVShow>()
                            tvShows.addAll(tvShowsResult)
                            tvShowsLiveData.postValue(ApiResponse.success(tvShows))
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

            override fun onFailure(call: Call<TVShowPopularResponse>, t: Throwable) {
                tvShowsLiveData.postValue(null)
                if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow()) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
        return tvShowsLiveData
    }
}
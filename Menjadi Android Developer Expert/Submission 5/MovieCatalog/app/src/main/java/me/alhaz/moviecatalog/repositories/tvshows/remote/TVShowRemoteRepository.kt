package me.alhaz.moviecatalog.repositories.tvshows.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.moviecatalog.BuildConfig
import me.alhaz.moviecatalog.helper.EspressoIdlingResource
import me.alhaz.moviecatalog.helper.RetrofitConfig
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.remote.response.TVShowPopularResponse
import me.alhaz.moviecatalog.valueobject.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowRemoteRepository {

    fun getListTVShow(): MutableLiveData<ArrayList<TVShow>> {
        EspressoIdlingResource.increment()
        val tvShowsLiveData = MutableLiveData<ArrayList<TVShow>>()
        RetrofitConfig().getTVShowService().getListTVShow(BuildConfig.API_KEY, BuildConfig.LANGUAGE).enqueue(object: Callback<TVShowPopularResponse> {
            override fun onResponse(call: Call<TVShowPopularResponse>, response: Response<TVShowPopularResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { tvShowPopularResponse ->
                        tvShowPopularResponse.results?.let { tvShowsResult ->
                            val tvShows = ArrayList<TVShow>()
                            tvShows.addAll(tvShowsResult)
                            tvShowsLiveData.postValue(tvShows)
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

    fun getDetailTVShow(tvShowID: Long) : MutableLiveData<TVShow> {
        EspressoIdlingResource.increment()
        val tvShowLiveData = MutableLiveData<TVShow>()
        RetrofitConfig().getTVShowService().getDetailTVShow(tvShowID, BuildConfig.API_KEY, BuildConfig.LANGUAGE).enqueue(object: Callback<TVShow> {
            override fun onResponse(call: Call<TVShow>, response: Response<TVShow>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { movie ->
                        tvShowLiveData.postValue(movie)
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

            override fun onFailure(call: Call<TVShow>, t: Throwable) {
                tvShowLiveData.postValue(null)
                if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow()) {
                    EspressoIdlingResource.decrement()
                }
            }
        })
        return tvShowLiveData
    }

    fun searchTVShow(name: String) : LiveData<ArrayList<TVShow>> {
        EspressoIdlingResource.increment()
        val tvShowsLiveData = MutableLiveData<ArrayList<TVShow>>()
        RetrofitConfig().getTVShowService().searchTVShow(BuildConfig.API_KEY, BuildConfig.LANGUAGE, name).enqueue(object: Callback<TVShowPopularResponse> {
            override fun onResponse(call: Call<TVShowPopularResponse>, response: Response<TVShowPopularResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let { moviePopularResponse ->
                        moviePopularResponse.results?.let { moviesResult ->
                            val tvShows = ArrayList<TVShow>()
                            tvShows.addAll(moviesResult)
                            tvShowsLiveData.postValue(tvShows)
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
package me.alhaz.footballclubunittesting.helper.API

import me.alhaz.footballclubunittesting.model.Parser.EventResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepository {

    fun getPreviousEvent(id: String, callback: APICallback<EventResponse?>) {
        APIService.init().getPreviousEvent(id).enqueue(object: Callback<EventResponse?> {
            override fun onFailure(call: Call<EventResponse?>, t: Throwable) {
                callback.onDataError(t.message)
            }
            override fun onResponse(call: Call<EventResponse?>, response: Response<EventResponse?>) {
                response?.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    }
                    else {
                        callback.onDataError(it.message())
                    }
                }
            }
        })
    }

    fun getNextEvent(id: String, callback: APICallback<EventResponse?>) {
        APIService.init().getNextEvent(id).enqueue(object: Callback<EventResponse?> {
            override fun onFailure(call: Call<EventResponse?>, t: Throwable) {
                callback.onDataError(t.message)
            }
            override fun onResponse(call: Call<EventResponse?>, response: Response<EventResponse?>) {
                response?.let {
                    if (it.isSuccessful) {
                        callback.onDataLoaded(it.body())
                    }
                    else {
                        callback.onDataError(it.message())
                    }
                }
            }
        })
    }

}
package me.alhaz.submission2.helper

import me.alhaz.submission2.model.EventResponse
import me.alhaz.submission2.model.Event
import me.alhaz.submission2.model.TeamResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("eventspastleague.php") //4328
    fun getPreviousEvent(@Query("id") id: String): Call<EventResponse>

    @GET("eventsnextleague.php") //4328
    fun getNextEvent(@Query("id") id: String): Call<EventResponse>

    @GET("lookupevent.php")
    fun getDetailEvent(@Query("id") id: String): Call<EventResponse>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String): Call<TeamResponse>

}

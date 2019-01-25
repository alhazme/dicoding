package me.alhaz.footballclubunittesting.helper.API

import me.alhaz.footballclubunittesting.model.Parser.EventResponse
import me.alhaz.footballclubunittesting.model.Parser.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    // Event

    @GET("eventspastleague.php") //4328
    fun getPreviousEvent(@Query("id") id: String): Call<EventResponse>

    @GET("eventsnextleague.php") //4328
    fun getNextEvent(@Query("id") id: String): Call<EventResponse>

    @GET("lookupevent.php")
    fun getDetailEvent(@Query("id") id: String): Call<EventResponse>

    // Team

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id: String): Call<TeamResponse>

}

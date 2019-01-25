package me.alhaz.footballclubunittesting.model.Parser

import com.google.gson.annotations.SerializedName

data class EventResponse (

        @SerializedName("events")
        var events: List<Event>? = null
)

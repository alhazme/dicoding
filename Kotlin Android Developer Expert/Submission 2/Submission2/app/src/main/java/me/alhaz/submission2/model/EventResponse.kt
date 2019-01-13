package me.alhaz.submission2.model

import com.google.gson.annotations.SerializedName

data class EventResponse (

        @SerializedName("events")
        var events: List<Event>? = null
)

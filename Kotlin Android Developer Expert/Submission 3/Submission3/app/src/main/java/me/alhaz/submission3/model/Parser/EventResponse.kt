package me.alhaz.submission3.model.Parser

import com.google.gson.annotations.SerializedName

data class EventResponse (

        @SerializedName("events")
        var events: List<Event>? = null
)

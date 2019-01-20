package me.alhaz.submission3.model.Parser

import com.google.gson.annotations.SerializedName


data class TeamResponse (

        @SerializedName("teams")
        var teams: List<Team>? = null
)
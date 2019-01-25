package me.alhaz.footballclubunittesting.model.Parser

import com.google.gson.annotations.SerializedName


data class TeamResponse (

        @SerializedName("teams")
        var teams: List<Team>? = null
)
package me.alhaz.snippet.movieapp.repositories.tvshows.local.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season (

    @SerializedName("air_date")
    val airDate: String = "",

    @SerializedName("episode_count")
    val episodeCount: Long = 0,

    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("season_number")
    val seasonNumber: Long = 0

) : Parcelable
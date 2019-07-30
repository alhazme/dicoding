package me.alhaz.snippet.movieapp.repositories.tvshows.local.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LastEpisodeToAir (

    @SerializedName("air_date")
    val airDate: String = "",

    @SerializedName("episode_number")
    val episodeNumber: Long = 0,

    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("production_code")
    val productionCode: String = "",

    @SerializedName("season_number")
    val seasonNumber: Long = 0,

    @SerializedName("show_id")
    val showID: Long = 0,

    @SerializedName("vote_average")
    val voteAverage: Long = 0,

    @SerializedName("vote_count")
    val voteCount: Long = 0
) : Parcelable
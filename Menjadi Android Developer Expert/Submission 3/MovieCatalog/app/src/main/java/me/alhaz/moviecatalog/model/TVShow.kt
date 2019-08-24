package me.alhaz.moviecatalog.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow (

    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("backdrop_path")
    val backdropPath: String = "",

    @SerializedName("first_air_date")
    val firstAirDate: String = "",

    @SerializedName("homepage")
    val homepage: String = "",

    @SerializedName("in_production")
    val inProduction: Boolean = false,

    @SerializedName("last_air_date")
    val lastAirDate: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Long = 0,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Long = 0,

    @SerializedName("original_language")
    val originalLanguage: String = "",

    @SerializedName("original_name")
    val originalName: String = "",

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("overview_id")
    val overview_id: String = "",

    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("status")
    val status: String = "",

    @SerializedName("type")
    val type: String = "",

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    val voteCount: Long = 0,

    @SerializedName("favorite")
    val favorite: Int = 0

): Parcelable
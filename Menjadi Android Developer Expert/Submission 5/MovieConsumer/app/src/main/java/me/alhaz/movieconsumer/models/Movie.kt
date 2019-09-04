package me.alhaz.movieconsumer.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    @SerializedName("id")
    var id: Long = 0,

    @SerializedName("adult")
    var adult: Boolean = false,

    @SerializedName("backdrop_path")
    var backdropPath: String = "",

    @SerializedName("budget")
    var budget: Long = 0,

    @SerializedName("homepage")
    var homepage: String = "",

    @SerializedName("imdbID")
    var imdbID: String = "",

    @SerializedName("original_language")
    var originalLanguage: String = "",

    @SerializedName("original_title")
    var originalTitle: String = "",

    @SerializedName("overview")
    var overview: String = "",

    @SerializedName("overview_id")
    var overview_id: String = "",

    @SerializedName("popularity")
    var popularity: Double = 0.0,

    @SerializedName("poster_path")
    var posterPath: String = "",

    @SerializedName("release_date")
    var releaseDate: String = "",

    @SerializedName("revenue")
    var revenue: Long = 0,

    @SerializedName("runtime")
    var runtime: Long = 0,

    @SerializedName("status")
    var status: String = "",

    @SerializedName("tagline")
    var tagline: String = "",

    @SerializedName("title")
    var title: String = "",

    @SerializedName("video")
    var video: Boolean = false,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    var voteCount: Long = 0,

    @SerializedName("favorite")
    var favorite: Int = 0

): Parcelable
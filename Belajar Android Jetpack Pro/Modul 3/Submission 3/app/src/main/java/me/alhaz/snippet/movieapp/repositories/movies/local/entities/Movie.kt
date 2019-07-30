package me.alhaz.snippet.movieapp.repositories.movies.local.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    @SerializedName("adult")
    val adult: Boolean = false,

    @SerializedName("backdrop_path")
    val backdropPath: String = "",

    @SerializedName("budget")
    val budget: Long = 0,

    @SerializedName("homepage")
    val homepage: String = "",

    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("imdbID")
    val imdbID: String = "",

    @SerializedName("original_language")
    val originalLanguage: String = "",

    @SerializedName("original_title")
    val originalTitle: String = "",

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("release_date")
    val releaseDate: String = "",

    @SerializedName("revenue")
    val revenue: Long = 0,

    @SerializedName("runtime")
    val runtime: Long = 0,

    @SerializedName("status")
    val status: String = "",

    @SerializedName("tagline")
    val tagline: String = "",

    @SerializedName("title")
    val title: String,

    @SerializedName("video")
    val video: Boolean = false,

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    val voteCount: Long = 0

): Parcelable
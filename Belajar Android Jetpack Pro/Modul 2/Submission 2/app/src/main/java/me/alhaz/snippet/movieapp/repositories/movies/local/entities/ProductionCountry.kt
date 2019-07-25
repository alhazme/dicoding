package me.alhaz.snippet.movieapp.repositories.movies.local.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCountry (

    @SerializedName("iso_3166_1")
    val iso3166_1: String = "",

    @SerializedName("name")
    val name: String = ""

): Parcelable
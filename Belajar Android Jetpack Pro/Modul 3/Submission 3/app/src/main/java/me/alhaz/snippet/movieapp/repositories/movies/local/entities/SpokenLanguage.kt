package me.alhaz.snippet.movieapp.repositories.movies.local.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class SpokenLanguage (

    @SerializedName("iso639_1")
    val iso639_1: String = "",

    @SerializedName("name")
    val name: String = ""

): Parcelable
package me.alhaz.snippet.movieapp.repositories.movies.local.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre (

    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    val name: String = ""

): Parcelable
package me.alhaz.snippet.movieapp.repositories.tvshows.local.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Network (

    @SerializedName("name")
    val name: String = "",

    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("logo_path")
    val logoPath: String = "",

    @SerializedName("origin_country")
    val originCountry: String = ""

) : Parcelable
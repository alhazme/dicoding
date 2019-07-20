package me.alhaz.snippet.movieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow (

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("overview")
    var overview: String = "",

    @SerializedName("year")
    var year: Int = 0,

    @SerializedName("score")
    var score: Int = 0,

    @SerializedName("runtime")
    var runtime: String = "",

    @SerializedName("photo")
    var photo: String = ""

) : Parcelable
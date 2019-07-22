package me.alhaz.snippet.movieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Crew (

    @SerializedName("name")
    var name: String = "",

    @SerializedName("title")
    var title: String = ""
) : Parcelable
package me.alhaz.moviecatalog.repositories.tvshows.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow

@Parcelize
data class TVShowPopularResponse(

    @SerializedName("page")
    var page: Int = 1,

    @SerializedName("total_results")
    var totalResults: Int = 0,

    @SerializedName("total_pages")
    var totalPages: Int = 0,

    @SerializedName("results")
    var results: List<TVShow>? = null

): Parcelable
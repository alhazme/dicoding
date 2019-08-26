package me.alhaz.moviecatalog.repositories.movies.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import me.alhaz.snippet.movieapp.models.Movie

@Parcelize
data class MoviePopularResponse(

    @SerializedName("page")
    var page: Int = 1,

    @SerializedName("total_results")
    var totalResults: Int = 0,

    @SerializedName("total_pages")
    var totalPages: Int = 0,

    @SerializedName("results")
    var results: List<Movie>? = null

): Parcelable
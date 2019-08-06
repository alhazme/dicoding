package me.alhaz.snippet.movieapp.repositories.movies.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
//    @SerializedName("id")
    var id: Long = 0,

    @ColumnInfo(name = "adult")
//    @SerializedName("adult")
    var adult: Boolean = false,

    @ColumnInfo(name = "backdrop_path")
//    @SerializedName("backdrop_path")
    var backdropPath: String = "",

    @ColumnInfo(name = "budget")
//    @SerializedName("budget")
    var budget: Long = 0,

    @ColumnInfo(name = "homepage")
//    @SerializedName("homepage")
    var homepage: String = "",

    @ColumnInfo(name = "imdbID")
//    @SerializedName("imdbID")
    var imdbID: String = "",

    @ColumnInfo(name = "original_language")
//    @SerializedName("original_language")
    var originalLanguage: String = "",

    @ColumnInfo(name = "original_title")
//    @SerializedName("original_title")
    var originalTitle: String = "",

    @ColumnInfo(name = "overview")
//    @SerializedName("overview")
    var overview: String = "",

    @ColumnInfo(name = "popularity")
//    @SerializedName("popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
//    @SerializedName("poster_path")
    var posterPath: String = "",

    @ColumnInfo(name = "release_date")
//    @SerializedName("release_date")
    var releaseDate: String = "",

    @ColumnInfo(name = "revenue")
//    @SerializedName("revenue")
    var revenue: Long = 0,

    @ColumnInfo(name = "runtime")
//    @SerializedName("runtime")
    var runtime: Long = 0,

    @ColumnInfo(name = "status")
//    @SerializedName("status")
    var status: String = "",

    @ColumnInfo(name = "tagline")
//    @SerializedName("tagline")
    var tagline: String = "",

    @ColumnInfo(name = "title")
//    @SerializedName("title")
    var title: String = "",

    @ColumnInfo(name = "video")
//    @SerializedName("video")
    var video: Boolean = false,

    @ColumnInfo(name = "vote_average")
//    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @ColumnInfo(name = "vote_count")
//    @SerializedName("vote_count")
    var voteCount: Long = 0,

    @ColumnInfo(name = "favorite")
//    @SerializedName("favorite")
    var favorite: Int = 0

): Parcelable
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
    val id: Long = 0,

    @ColumnInfo(name = "adult")
//    @SerializedName("adult")
    val adult: Boolean = false,

    @ColumnInfo(name = "backdrop_path")
//    @SerializedName("backdrop_path")
    val backdropPath: String = "",

    @ColumnInfo(name = "budget")
//    @SerializedName("budget")
    val budget: Long = 0,

    @ColumnInfo(name = "homepage")
//    @SerializedName("homepage")
    val homepage: String = "",

    @ColumnInfo(name = "imdbID")
//    @SerializedName("imdbID")
    val imdbID: String = "",

    @ColumnInfo(name = "original_language")
//    @SerializedName("original_language")
    val originalLanguage: String = "",

    @ColumnInfo(name = "original_title")
//    @SerializedName("original_title")
    val originalTitle: String = "",

    @ColumnInfo(name = "overview")
//    @SerializedName("overview")
    val overview: String = "",

    @ColumnInfo(name = "popularity")
//    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
//    @SerializedName("poster_path")
    val posterPath: String = "",

    @ColumnInfo(name = "release_date")
//    @SerializedName("release_date")
    val releaseDate: String = "",

    @ColumnInfo(name = "revenue")
//    @SerializedName("revenue")
    val revenue: Long = 0,

    @ColumnInfo(name = "runtime")
//    @SerializedName("runtime")
    val runtime: Long = 0,

    @ColumnInfo(name = "status")
//    @SerializedName("status")
    val status: String = "",

    @ColumnInfo(name = "tagline")
//    @SerializedName("tagline")
    val tagline: String = "",

    @ColumnInfo(name = "title")
//    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "video")
//    @SerializedName("video")
    val video: Boolean = false,

    @ColumnInfo(name = "vote_average")
//    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @ColumnInfo(name = "vote_count")
//    @SerializedName("vote_count")
    val voteCount: Long = 0,

    @ColumnInfo(name = "favorite")
//    @SerializedName("favorite")
    val favorite: Int = 0

): Parcelable
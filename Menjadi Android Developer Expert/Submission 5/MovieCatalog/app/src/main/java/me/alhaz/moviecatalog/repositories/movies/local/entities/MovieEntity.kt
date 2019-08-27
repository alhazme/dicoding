package me.alhaz.moviecatalog.repositories.movies.local.entities

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
    var id: Long = 0,

    @ColumnInfo(name = "adult")
    var adult: Boolean = false,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String = "",

    @ColumnInfo(name = "budget")
    var budget: Long = 0,

    @ColumnInfo(name = "homepage")
    var homepage: String = "",

    @ColumnInfo(name = "imdbID")
    var imdbID: String = "",

    @ColumnInfo(name = "original_language")
    var originalLanguage: String = "",

    @ColumnInfo(name = "original_title")
    var originalTitle: String = "",

    @ColumnInfo(name = "overview")
    var overview: String = "",

    @ColumnInfo(name = "overview_id")
    var overview_id: String = "",

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
    var posterPath: String = "",

    @ColumnInfo(name = "release_date")
    var releaseDate: String = "",

    @ColumnInfo(name = "revenue")
    var revenue: Long = 0,

    @ColumnInfo(name = "runtime")
    var runtime: Long = 0,

    @ColumnInfo(name = "status")
    var status: String = "",

    @ColumnInfo(name = "tagline")
    var tagline: String = "",

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "video")
    var video: Boolean = false,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0,

    @ColumnInfo(name = "vote_count")
    var voteCount: Long = 0,

    @ColumnInfo(name = "favorite")
    var favorite: Int = 0

): Parcelable
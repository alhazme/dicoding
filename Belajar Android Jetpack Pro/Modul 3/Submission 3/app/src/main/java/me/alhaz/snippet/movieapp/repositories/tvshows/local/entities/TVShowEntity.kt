package me.alhaz.snippet.movieapp.repositories.tvshows.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tvshow")
data class TVShowEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
//    @SerializedName("id")
    val id: Long = 0,

    @ColumnInfo(name = "backdrop_path")
//    @SerializedName("backdrop_path")
    val backdropPath: String = "",

    @ColumnInfo(name = "first_air_date")
//    @SerializedName("first_air_date")
    val firstAirDate: String = "",

    @ColumnInfo(name = "homepage")
//    @SerializedName("homepage")
    val homepage: String = "",

    @ColumnInfo(name = "in_production")
//    @SerializedName("in_production")
    val inProduction: Boolean = false,

    @ColumnInfo(name = "last_air_date")
//    @SerializedName("last_air_date")
    val lastAirDate: String = "",

    @ColumnInfo(name = "name")
//    @SerializedName("name")
    val name: String = "",

    @ColumnInfo(name = "number_of_episodes")
//    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Long = 0,

    @ColumnInfo(name = "number_of_seasons")
//    @SerializedName("number_of_seasons")
    val numberOfSeasons: Long = 0,

    @ColumnInfo(name = "original_language")
//    @SerializedName("original_language")
    val originalLanguage: String = "",

    @ColumnInfo(name = "original_name")
//    @SerializedName("original_name")
    val originalName: String = "",

    @ColumnInfo(name = "overview")
//    @SerializedName("overview")
    val overview: String = "",

    @ColumnInfo(name = "popularity")
//    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
//    @SerializedName("poster_path")
    val posterPath: String = "",

    @ColumnInfo(name = "status")
//    @SerializedName("status")
    val status: String = "",

    @ColumnInfo(name = "type")
//    @SerializedName("type")
    val type: String = "",

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
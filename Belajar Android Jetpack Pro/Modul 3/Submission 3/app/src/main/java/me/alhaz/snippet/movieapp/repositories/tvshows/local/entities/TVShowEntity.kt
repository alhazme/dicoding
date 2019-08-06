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
    var id: Long = 0,

    @ColumnInfo(name = "backdrop_path")
//    @SerializedName("backdrop_path")
    var backdropPath: String = "",

    @ColumnInfo(name = "first_air_date")
//    @SerializedName("first_air_date")
    var firstAirDate: String = "",

    @ColumnInfo(name = "homepage")
//    @SerializedName("homepage")
    var homepage: String = "",

    @ColumnInfo(name = "in_production")
//    @SerializedName("in_production")
    var inProduction: Boolean = false,

    @ColumnInfo(name = "last_air_date")
//    @SerializedName("last_air_date")
    var lastAirDate: String = "",

    @ColumnInfo(name = "name")
//    @SerializedName("name")
    var name: String = "",

    @ColumnInfo(name = "number_of_episodes")
//    @SerializedName("number_of_episodes")
    var numberOfEpisodes: Long = 0,

    @ColumnInfo(name = "number_of_seasons")
//    @SerializedName("number_of_seasons")
    var numberOfSeasons: Long = 0,

    @ColumnInfo(name = "original_language")
//    @SerializedName("original_language")
    var originalLanguage: String = "",

    @ColumnInfo(name = "original_name")
//    @SerializedName("original_name")
    var originalName: String = "",

    @ColumnInfo(name = "overview")
//    @SerializedName("overview")
    var overview: String = "",

    @ColumnInfo(name = "popularity")
//    @SerializedName("popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
//    @SerializedName("poster_path")
    var posterPath: String = "",

    @ColumnInfo(name = "status")
//    @SerializedName("status")
    var status: String = "",

    @ColumnInfo(name = "type")
//    @SerializedName("type")
    var type: String = "",

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
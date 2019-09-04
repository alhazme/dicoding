package me.alhaz.moviecatalog.repositories.tvshows.local.entities

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
    var id: Long = 0,

    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String = "",

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String = "",

    @ColumnInfo(name = "homepage")
    var homepage: String = "",

    @ColumnInfo(name = "in_production")
    var inProduction: Boolean = false,

    @ColumnInfo(name = "last_air_date")
    var lastAirDate: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "number_of_episodes")
    var numberOfEpisodes: Long = 0,

    @ColumnInfo(name = "number_of_seasons")
    var numberOfSeasons: Long = 0,

    @ColumnInfo(name = "original_language")
    var originalLanguage: String = "",

    @ColumnInfo(name = "original_name")
    var originalName: String = "",

    @ColumnInfo(name = "overview")
    var overview: String = "",

    @ColumnInfo(name = "overview_id")
    var overview_id: String = "",

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "poster_path")
    var posterPath: String = "",

    @ColumnInfo(name = "status")
    var status: String = "",

    @ColumnInfo(name = "type")
    var type: String = "",

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0,

    @ColumnInfo(name = "vote_count")
    var voteCount: Long = 0,

    @ColumnInfo(name = "favorite")
    var favorite: Int = 0

): Parcelable
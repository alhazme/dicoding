package me.alhaz.snippet.movieapp.repositories.tvshows.local.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Genre
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.ProductionCountry

@Parcelize
data class TVShow (

    @SerializedName("backdrop_path")
    val backdropPath: String = "",

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Long>?,

    @SerializedName("first_air_date")
    val firstAirDate: String = "",

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("homepage")
    val homepage: String = "",

    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("in_production")
    val inProduction: Boolean,

    @SerializedName("languages")
    val languages: List<String>? = null,

    @SerializedName("last_air_date")
    val lastAirDate: String,

    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir?,

    @SerializedName("name")
    val name: String,

    @SerializedName("networks")
    val networks: List<Network>?,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Long = 0,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Long = 0,

    @SerializedName("origin_country")
    val originCountry: List<String>,

    @SerializedName("original_language")
    val originalLanguage: String = "",

    @SerializedName("original_name")
    val originalName: String = "",

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCountry>,

    @SerializedName("seasons")
    val seasons: List<Season>,

    @SerializedName("status")
    val status: String = "",

    @SerializedName("type")
    val type: String = "",

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    val voteCount: Long = 0

): Parcelable
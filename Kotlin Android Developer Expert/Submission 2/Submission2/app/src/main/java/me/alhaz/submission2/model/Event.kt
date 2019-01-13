package me.alhaz.submission2.model

import com.google.gson.annotations.SerializedName

data class Event (

        @SerializedName("idEvent")
        var idEvent: String? = "",

        @SerializedName("idSoccerXML")
        var idSoccerXML: String? = "",

        @SerializedName("strEvent")
        var strEvent: String? = "",

        @SerializedName("strSport")
        var strSport: String? = "",

        @SerializedName("idLeague")
        var idLeague: String? = "",

        @SerializedName("strLeague")
        var strLeague: String? = "",

        @SerializedName("strSeason")
        var strSeason: String? = "",

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = "",

        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = "",

        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = "",

        @SerializedName("intHomeScore")
        var intHomeScore: Int? = 0,

        @SerializedName("intRound")
        var intRound: Int? = 0,

        @SerializedName("intAwayScore")
        var intAwayScore: Int? = 0,

        @SerializedName("intSpectators")
        var intSpectators: Int? = 0,

        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = "",

        @SerializedName("strHomeRedCards")
        var strHomeRedCards: String? = "",

        @SerializedName("strHomeYellowCards")
        var strHomeYellowCards: String? = "",

        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = "",

        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = "",

        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String? = "",

        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = "",

        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = "",

        @SerializedName("strHomeFormation")
        var strHomeFormation: String? = "",

        @SerializedName("strAwayRedCards")
        var strAwayRedCards: String? = "",

        @SerializedName("strAwayYellowCards")
        var strAwayYellowCards: String? = "",

        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = "",

        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = "",

        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = "",

        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String? = "",

        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = "",

        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = "",

        @SerializedName("strAwayFormation")
        var strAwayFormation: String? = "",

        @SerializedName("intHomeShots")
        var intHomeShots: Int? = 0,

        @SerializedName("intAwayShots")
        var intAwayShots: Int? = 0,

        @SerializedName("dateEvent")
        var dateEvent: String? = "",

        @SerializedName("strDate")
        var strDate: String? = "",

        @SerializedName("strTime")
        var strTime: String? = "",

        @SerializedName("strTVStation")
        var strTVStation: String? = "",

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = "",

        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = "",

        @SerializedName("strResult")
        var strResult: String? = "",

        @SerializedName("strCircuit")
        var strCircuit: String? = "",

        @SerializedName("strCountry")
        var strCountry: String? = "",

        @SerializedName("strCity")
        var strCity: String? = "",

        @SerializedName("strPoster")
        var strPoster: String? = "",

        @SerializedName("strFanart")
        var strFanart: String? = "",

        @SerializedName("strThumb")
        var strThumb: String? = "",

        @SerializedName("strBanner")
        var strBanner: String? = "",

        @SerializedName("strMap")
        var strMap: String? = "",

        @SerializedName("strLocked")
        var strLocked: String? = ""
)
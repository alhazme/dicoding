package me.alhaz.submission2.model

import com.google.gson.annotations.SerializedName


data class Team (

        @SerializedName("idTeam")
        var idTeam: String? = "",

        @SerializedName("idSoccerXML")
        var idSoccerXML: String? = "",

        @SerializedName("intLoved")
        var strEvent: Int? = 0,

        @SerializedName("strTeam")
        var strTeam: String? = "",

        @SerializedName("strTeamShort")
        var strTeamShort: String? = "",

        @SerializedName("strAlternate")
        var strAlternate: String? = "",

        @SerializedName("intFormedYear")
        var intFormedYear: Int? = 0,

        @SerializedName("strSport")
        var strSport: String? = "",

        @SerializedName("strLeague")
        var strLeague: String? = "",

        @SerializedName("idLeague")
        var idLeague: String? = "",

        @SerializedName("strDivision")
        var strDivision: Int? = 0,

        @SerializedName("intRound")
        var intRound: Int? = 0,

        @SerializedName("strManager")
        var strManager: String? = "",

        @SerializedName("strStadium")
        var strStadium: String? = "",

        @SerializedName("strKeywords")
        var strKeywords: String? = "",

        @SerializedName("strRSS")
        var strRSS: String? = "",

        @SerializedName("strStadiumThumb")
        var strStadiumThumb: String? = "",

        @SerializedName("strStadiumDescription")
        var strStadiumDescription: String? = "",

        @SerializedName("strStadiumLocation")
        var strStadiumLocation: String? = "",

        @SerializedName("intStadiumCapacity")
        var intStadiumCapacity: Int? = 0,

        @SerializedName("strWebsite")
        var strWebsite: String? = "",

        @SerializedName("strFacebook")
        var strFacebook: String? = "",

        @SerializedName("strTwitter")
        var strTwitter: String? = "",

        @SerializedName("strInstagram")
        var strInstagram: String? = "",

        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String? = "",

        @SerializedName("strDescriptionDE")
        var strDescriptionDE: String? = "",

        @SerializedName("strDescriptionFR")
        var strDescriptionFR: String? = "",

        @SerializedName("strDescriptionCN")
        var strDescriptionCN: String? = "",

        @SerializedName("strDescriptionIT")
        var strDescriptionIT: String? = "",

        @SerializedName("strDescriptionJP")
        var strDescriptionJP: String? = "",

        @SerializedName("strDescriptionRU")
        var strDescriptionRU: String? = "",

        @SerializedName("strDescriptionES")
        var strDescriptionES: String? = "",

        @SerializedName("strDescriptionPT")
        var strDescriptionPT: String? = "",

        @SerializedName("strDescriptionSE")
        var strDescriptionSE: String? = "",

        @SerializedName("strDescriptionNL")
        var strDescriptionNL: String? = "",

        @SerializedName("strDescriptionHU")
        var strDescriptionHU: String? = "",

        @SerializedName("strDescriptionNO")
        var strDescriptionNO: String? = "",

        @SerializedName("strDescriptionIL")
        var strDescriptionIL: String? = "",

        @SerializedName("strDescriptionPL")
        var strDescriptionPL: String? = "",

        @SerializedName("strGender")
        var strGender: String? = "",

        @SerializedName("strTeamBadge")
        var strTeamBadge: String? = "",

        @SerializedName("strTeamJersey")
        var strTeamJersey: String? = "",

        @SerializedName("strTeamLogo")
        var strTeamLogo: String? = "",

        @SerializedName("strTeamFanart1")
        var strTeamFanart1: String? = "",

        @SerializedName("strTeamFanart2")
        var strTeamFanart2: String? = "",

        @SerializedName("strTeamFanart3")
        var strTeamFanart3: String? = "",

        @SerializedName("strTeamFanart4")
        var strTeamFanart4: String? = "",

        @SerializedName("strTeamBanner")
        var strTeamBanner: String? = "",

        @SerializedName("strYoutube")
        var strYoutube: String? = "",

        @SerializedName("strLocked")
        var strLocked: String? = ""
)
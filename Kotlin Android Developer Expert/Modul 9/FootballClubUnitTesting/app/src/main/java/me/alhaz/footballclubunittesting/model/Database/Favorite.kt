package me.alhaz.footballclubunittesting.model.Database

data class Favorite(
        val id: Long?,
        val eventId: String?,
        val date: String?,
        val teamHomeId: String?,
        val teamHomeName: String?,
        val teamHomeScore: Int?,
        val teamAwayId: String?,
        val teamAwayName: String?,
        val teamAwayScore: Int?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val DATE: String = "DATE"
        const val TEAM_HOME_ID: String = "TEAM_HOME_ID"
        const val TEAM_HOME_NAME: String = "TEAM_HOME_NAME"
        const val TEAM_HOME_SCORE: String = "TEAM_HOME_SCORE"
        const val TEAM_AWAY_ID: String = "TEAM_AWAY_ID"
        const val TEAM_AWAY_NAME: String = "TEAM_AWAY_NAME"
        const val TEAM_AWAY_SCORE: String = "TEAM_AWAY_SCORE"

    }

}
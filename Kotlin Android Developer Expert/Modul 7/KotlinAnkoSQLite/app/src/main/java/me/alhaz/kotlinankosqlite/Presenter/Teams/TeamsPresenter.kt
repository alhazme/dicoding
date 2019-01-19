package me.alhaz.kotlinankosqlite.Presenter.Teams

import com.google.gson.Gson
import me.alhaz.kotlinankosqlite.Helper.ApiRepository
import me.alhaz.kotlinankosqlite.Helper.TheSportDBApi
import me.alhaz.kotlinankosqlite.Model.Parser.Team
import me.alhaz.kotlinankosqlite.Model.Parser.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface TeamsView {

    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}

class TeamsPresenter(private val view: TeamsView, private val apiRepository: ApiRepository, private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}
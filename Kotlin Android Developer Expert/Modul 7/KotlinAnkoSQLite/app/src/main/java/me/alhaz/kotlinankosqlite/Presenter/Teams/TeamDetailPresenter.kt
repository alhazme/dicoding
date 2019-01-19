package me.alhaz.kotlinankosqlite.Presenter.Teams

import com.google.gson.Gson
import me.alhaz.kotlinankosqlite.Helper.ApiRepository
import me.alhaz.kotlinankosqlite.Helper.TheSportDBApi
import me.alhaz.kotlinankosqlite.Model.Parser.Team
import me.alhaz.kotlinankosqlite.Model.Parser.TeamResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}

class TeamDetailPresenter(private val view: TeamDetailView, private val apiRepository: ApiRepository, private val gson: Gson) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)), TeamResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showTeamDetail(data.teams)
            }
        }
    }

}
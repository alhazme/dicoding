package me.alhaz.footballclubunittesting.presenter.detail

import android.content.Context
import android.util.Log
import android.database.sqlite.SQLiteConstraintException
import me.alhaz.footballclubunittesting.helper.API.RetrofitConfig
import me.alhaz.footballclubunittesting.helper.Database.database
import me.alhaz.footballclubunittesting.model.Database.Favorite
import me.alhaz.footballclubunittesting.model.Parser.Event
import me.alhaz.footballclubunittesting.model.Parser.EventResponse
import me.alhaz.footballclubunittesting.model.Parser.Team
import me.alhaz.footballclubunittesting.model.Parser.TeamResponse
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter: DetailContract.Presenter {

    var view: DetailContract.View? = null

    override fun loadEvent(id: String) {
        RetrofitConfig().getService().getDetailEvent(id).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("tag", "responsennya ${responseData}")

                    var data = arrayListOf<Event>()
                    responseData?.let {
                        val eventResponse: EventResponse = it
                        eventResponse.events?.let {
                            val events: List<Event> = it
                            if (events.isNotEmpty()) {
                                events.forEach {
                                    val event: Event = it
                                    data.add(event)
                                }
                            }
                        }
                    }
                    view?.showEventData(data)
                    view?.hideProgress()
                }
            }
            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.e("tag", "errornya ${t.message}")
                view?.hideProgress()
                view?.showAlert(t.message)
            }
        })
    }

    override fun loadTeam(id: String, type: String) {
        RetrofitConfig().getService().getDetailTeam(id).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("tag", "responsennya ${responseData}")

                    var data = arrayListOf<Team>()
                    responseData?.let {
                        val teamResponse: TeamResponse = it
                        teamResponse.teams?.let {
                            val teams: List<Team> = it
                            if (teams.isNotEmpty()) {
                                teams.forEach {
                                    val team: Team = it
                                    data.add(team)
                                }
                            }
                        }
                    }

                    view?.showEventTeam(data, type)
                    view?.hideProgress()
                }
            }
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e("tag", "errornya ${t.message}")
                view?.hideProgress()
                view?.showAlert(t.message)
            }
        })
    }

    override fun isFavorite(context: Context, id: String): Boolean {
        val database = context.database
        var isFavorite = false
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(EVENT_ID = {id})", "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
        return isFavorite
    }

    override fun addToFavorite(context: Context, id: String, date: String, teamHomeId: String, teamHomeName: String, teamHomeScore: Int, teamAwayId: String, teamAwayName: String, teamAwayScore: Int) {
        val database = context.database
        try {
            database.use {
                insert(
                        Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to id,
                        Favorite.DATE to date,
                        Favorite.TEAM_HOME_ID to teamHomeId,
                        Favorite.TEAM_HOME_NAME to teamHomeName,
                        Favorite.TEAM_HOME_SCORE to teamHomeScore,
                        Favorite.TEAM_AWAY_ID to teamAwayId,
                        Favorite.TEAM_AWAY_NAME to teamAwayName,
                        Favorite.TEAM_AWAY_SCORE to teamAwayScore
                )
            }
            view?.showAlert("Added to favorite")
//            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            view?.showAlert(e.localizedMessage)
        }
    }

    override fun removeFromFavorite(context: Context, id: String) {
        val database = context.database
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to id)
            }
            view?.showAlert("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            view?.showAlert(e.localizedMessage)
        }

    }

    override fun takeView(view: DetailContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

}
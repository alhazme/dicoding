package me.alhaz.submission2.presenter.detail

import android.util.Log
import me.alhaz.submission2.helper.RetrofitConfig
import me.alhaz.submission2.model.Event
import me.alhaz.submission2.model.EventResponse
import me.alhaz.submission2.model.Team
import me.alhaz.submission2.model.TeamResponse
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

    override fun takeView(view: DetailContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

}
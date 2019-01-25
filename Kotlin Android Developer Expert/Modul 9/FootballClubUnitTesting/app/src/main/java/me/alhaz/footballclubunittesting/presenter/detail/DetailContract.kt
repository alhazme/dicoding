package me.alhaz.footballclubunittesting.presenter.detail

import android.content.Context
import me.alhaz.footballclubunittesting.model.Parser.Event
import me.alhaz.footballclubunittesting.model.Parser.Team
import me.alhaz.footballclubunittesting.presenter.base.BasePresenter
import me.alhaz.footballclubunittesting.view.base.BaseView


interface DetailContract {

    interface View: BaseView<Presenter> {
        fun showEventData(list: ArrayList<Event>)
        fun showEventTeam(list: ArrayList<Team>, type: String)
    }

    interface Presenter: BasePresenter<View> {
        fun loadTeam(id: String, type: String)
        fun loadEvent(id: String)

        fun isFavorite(context: Context, id: String): Boolean
        fun addToFavorite(context: Context, id: String, date: String, teamHomeId: String, teamHomeName: String, teamHomeScore: Int, teamAwayId: String, teamAwayName: String, teamAwayScore: Int)
        fun removeFromFavorite(context: Context, id: String)
    }
}
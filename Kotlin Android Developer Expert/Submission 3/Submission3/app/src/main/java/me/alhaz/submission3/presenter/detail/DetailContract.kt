package me.alhaz.submission3.presenter.detail

import android.content.Context
import me.alhaz.submission3.model.Parser.Event
import me.alhaz.submission3.model.Parser.Team
import me.alhaz.submission3.presenter.base.BasePresenter
import me.alhaz.submission3.view.base.BaseView


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
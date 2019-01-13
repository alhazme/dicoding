package me.alhaz.submission2.presenter.detail

import me.alhaz.submission2.model.Event
import me.alhaz.submission2.model.Team
import me.alhaz.submission2.presenter.base.BasePresenter
import me.alhaz.submission2.view.base.BaseView


interface DetailContract {

    interface View: BaseView<Presenter> {
        fun showEventData(list: ArrayList<Event>)
        fun showEventTeam(list: ArrayList<Team>, type: String)
    }

    interface Presenter: BasePresenter<View> {
        fun loadTeam(id: String, type: String)
        fun loadEvent(id: String)
    }
}
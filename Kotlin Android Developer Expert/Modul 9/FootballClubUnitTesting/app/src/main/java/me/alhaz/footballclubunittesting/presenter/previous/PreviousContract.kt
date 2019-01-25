package me.alhaz.footballclubunittesting.presenter.previous

import me.alhaz.footballclubunittesting.model.Parser.Event
import me.alhaz.footballclubunittesting.presenter.base.BasePresenter
import me.alhaz.footballclubunittesting.view.base.BaseView

interface PreviousContract {

    interface View: BaseView<Presenter> {
        fun showDatas(list: ArrayList<Event>)
    }

    interface Presenter: BasePresenter<View> {
        fun loadEvent(id: String)
    }
}
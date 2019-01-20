package me.alhaz.submission3.presenter.previous

import me.alhaz.submission3.model.Parser.Event
import me.alhaz.submission3.presenter.base.BasePresenter
import me.alhaz.submission3.view.base.BaseView

interface PreviousContract {

    interface View: BaseView<Presenter> {
        fun showDatas(list: ArrayList<Event>)
    }

    interface Presenter: BasePresenter<View> {
        fun loadEvent(id: String)
    }
}
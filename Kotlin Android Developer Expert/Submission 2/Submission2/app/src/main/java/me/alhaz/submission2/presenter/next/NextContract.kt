package me.alhaz.submission2.presenter.next

import me.alhaz.submission2.model.Event
import me.alhaz.submission2.presenter.base.BasePresenter
import me.alhaz.submission2.view.base.BaseView

interface NextContract {

    interface View: BaseView<Presenter> {
        fun showDatas(list: ArrayList<Event>)
    }

    interface Presenter: BasePresenter<View> {
        fun loadEvent(id: String)
    }
}
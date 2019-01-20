package me.alhaz.submission3.presenter.favorite

import android.content.Context
import me.alhaz.submission3.model.Database.Favorite
import me.alhaz.submission3.presenter.base.BasePresenter
import me.alhaz.submission3.view.base.BaseView

interface FavoriteContract {

    interface View: BaseView<Presenter> {
        fun showDatas(list: ArrayList<Favorite>)
    }

    interface Presenter: BasePresenter<View> {
        fun loadEvent(context: Context)
    }
}
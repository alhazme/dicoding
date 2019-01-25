package me.alhaz.footballclubunittesting.presenter.favorite

import android.content.Context
import me.alhaz.footballclubunittesting.model.Database.Favorite
import me.alhaz.footballclubunittesting.presenter.base.BasePresenter
import me.alhaz.footballclubunittesting.view.base.BaseView

interface FavoriteContract {

    interface View: BaseView<Presenter> {
        fun showDatas(list: ArrayList<Favorite>)
    }

    interface Presenter: BasePresenter<View> {
        fun loadEvent(context: Context)
    }
}
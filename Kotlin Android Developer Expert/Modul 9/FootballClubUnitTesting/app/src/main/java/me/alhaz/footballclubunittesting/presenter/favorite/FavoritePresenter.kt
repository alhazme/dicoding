package me.alhaz.footballclubunittesting.presenter.favorite

import android.content.Context
import me.alhaz.footballclubunittesting.helper.Database.database
import me.alhaz.footballclubunittesting.model.Database.Favorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritePresenter : FavoriteContract.Presenter {

    var view: FavoriteContract.View? = null

    override fun loadEvent(context: Context) {
        var data = arrayListOf<Favorite>()
        var database = context.database
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            var favorites: List<Favorite> = favorite.map { it };
            data.addAll(favorites)
        }
        view?.showDatas(data)
        view?.hideProgress()
    }

    override fun takeView(view: FavoriteContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

}
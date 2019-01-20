package me.alhaz.submission3.presenter.favorite

import android.content.Context
import android.util.Log
import me.alhaz.submission3.helper.RetrofitConfig
import me.alhaz.submission3.helper.database
import me.alhaz.submission3.model.Database.Favorite
import me.alhaz.submission3.model.Parser.Event
import me.alhaz.submission3.model.Parser.EventResponse
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
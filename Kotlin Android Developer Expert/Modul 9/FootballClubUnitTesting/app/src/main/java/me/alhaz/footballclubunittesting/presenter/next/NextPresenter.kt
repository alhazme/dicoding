package me.alhaz.footballclubunittesting.presenter.next

import android.util.Log
import me.alhaz.footballclubunittesting.helper.API.APICallback
import me.alhaz.footballclubunittesting.helper.API.APIRepository
import me.alhaz.footballclubunittesting.helper.API.RetrofitConfig
import me.alhaz.footballclubunittesting.model.Parser.Event
import me.alhaz.footballclubunittesting.model.Parser.EventResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NextPresenter(private val apiRepository: APIRepository) : NextContract.Presenter {

    var view: NextContract.View? = null

    override fun loadEvent(id: String) {
        view?.showProgress()
        apiRepository.getNextEvent(id, object: APICallback<EventResponse?> {
            override fun onDataError(message: String?) {
                view?.let {
                    it.hideProgress()
                    it.showAlert(message)
                }
            }
            override fun onDataLoaded(data: EventResponse?) {
                data?.let {
                    var events = arrayListOf<Event>()
                    data.events?.let {
                        events.addAll(it)
                        view?.showDatas(events)
                        view?.hideProgress()
                    }
                }
            }
        })
    }

    override fun takeView(view: NextContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

}
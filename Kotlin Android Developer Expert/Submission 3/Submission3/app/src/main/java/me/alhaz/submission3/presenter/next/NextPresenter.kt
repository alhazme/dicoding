package me.alhaz.submission3.presenter.next

import android.util.Log
import me.alhaz.submission3.helper.RetrofitConfig
import me.alhaz.submission3.model.Parser.Event
import me.alhaz.submission3.model.Parser.EventResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NextPresenter : NextContract.Presenter {

    var view: NextContract.View? = null

    override fun loadEvent(id: String) {

        RetrofitConfig().getService().getNextEvent(id).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("tag", "responsennya ${responseData}")

                    var data = arrayListOf<Event>()
                    responseData?.let {
                        val eventResponse: EventResponse = it
                        eventResponse.events?.let {
                            val events: List<Event> = it
                            if (events.isNotEmpty()) {
                                events.forEach {
                                    val event: Event = it
                                    data.add(event)
                                }
                            }
                        }
                    }
                    view?.showDatas(data)
                    view?.hideProgress()
                }
            }
            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.e("tag", "errornya ${t.message}")
                view?.hideProgress()
                view?.showAlert(t.message)
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
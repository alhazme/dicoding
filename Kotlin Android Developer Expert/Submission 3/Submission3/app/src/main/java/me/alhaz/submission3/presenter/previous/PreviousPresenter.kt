package me.alhaz.submission3.presenter.previous
import android.util.Log
import me.alhaz.submission3.helper.RetrofitConfig
import me.alhaz.submission3.model.Parser.Event
import me.alhaz.submission3.model.Parser.EventResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreviousPresenter : PreviousContract.Presenter {

    var view: PreviousContract.View? = null

    override fun loadEvent(id: String) {

        RetrofitConfig().getService().getPreviousEvent(id).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Log.d("tag", "responsennya ${responseData}")

                    var data = arrayListOf<Event>()
                    if (responseData?.events!!.isNotEmpty()) {
                        responseData?.events?.forEach {
                            data.add(it)
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

    override fun takeView(view: PreviousContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }

}
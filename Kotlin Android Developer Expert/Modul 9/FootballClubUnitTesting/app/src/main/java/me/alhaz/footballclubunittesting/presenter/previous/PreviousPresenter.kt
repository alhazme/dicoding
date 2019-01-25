package me.alhaz.footballclubunittesting.presenter.previous
import me.alhaz.footballclubunittesting.helper.API.APICallback
import me.alhaz.footballclubunittesting.helper.API.APIRepository
import me.alhaz.footballclubunittesting.model.Parser.Event
import me.alhaz.footballclubunittesting.model.Parser.EventResponse

class PreviousPresenter(private val apiRepository: APIRepository) : PreviousContract.Presenter {

    var view: PreviousContract.View? = null

    override fun loadEvent(id: String) {
        view?.showProgress()
        apiRepository.getPreviousEvent(id, object: APICallback<EventResponse>{
            override fun onDataError(message: String?) {
                view?.let {
                    it.hideProgress()
                    it.showAlert(message)
                }
            }
            override fun onDataLoaded(data: EventResponse) {
                var events = arrayListOf<Event>()
                data.events?.let {
                    events.addAll(it)
                    view?.showDatas(events)
                    view?.hideProgress()
                }
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
package me.alhaz.footballclubunittesting

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import me.alhaz.footballclubunittesting.helper.API.APICallback
import me.alhaz.footballclubunittesting.helper.API.APIRepository
import me.alhaz.footballclubunittesting.model.Parser.EventResponse
import me.alhaz.footballclubunittesting.presenter.previous.PreviousContract
import me.alhaz.footballclubunittesting.presenter.previous.PreviousPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PreviousEventUnitTest {

    @Mock
    private lateinit var view: PreviousContract.View

    @Mock
    private lateinit var response : EventResponse

    @Mock
    private lateinit var apiRepository: APIRepository

    private lateinit var presenter: PreviousContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PreviousPresenter(apiRepository)
        presenter.takeView(view)
    }

    @Test
    fun getPreviousEventTest() {
        val id = "4328"
        presenter.loadEvent(id)
        argumentCaptor<APICallback<EventResponse>>().apply {
            verify(apiRepository).getPreviousEvent(eq(id), capture())
            firstValue.onDataLoaded(response)
        }
        Mockito.verify(view).showProgress()
        Mockito.verify(view).showDatas(ArrayList(response.events))
        Mockito.verify(view).hideProgress()
    }

    @Test
    fun getPreviousEventErrorTest() {
        val id = ""
        presenter.loadEvent(id)
        argumentCaptor<APICallback<EventResponse>>().apply {
            verify(apiRepository).getPreviousEvent(eq(""), capture())
            firstValue.onDataError("ID Not Found")
        }
        Mockito.verify(view).showProgress()
        Mockito.verify(view).showAlert("ID Not Found")
        Mockito.verify(view).hideProgress()
    }

}
package me.alhaz.snippet.movieapp.views.tvshows.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.Mockito

class TVShowDetailViewModelTest {


    private lateinit var tvShowDetailViewModel: TVShowDetailViewModel
    private var tvShowRepository: TVShowRepository = Mockito.mock(TVShowRepository::class.java)
    private var dummyTVShow: TVShow = DataDummy.generateTVShows().get(0)
    private var tvShowID: Long = dummyTVShow.id

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        tvShowDetailViewModel = TVShowDetailViewModel()
        tvShowDetailViewModel?.tvShowRepository = tvShowRepository
    }

    @Test
    fun getTVShowDetail() {

        var tvShow = MutableLiveData<TVShow>()
        tvShow.value = dummyTVShow

        Mockito.`when`(tvShowRepository.getDetailTVShow(tvShowID)).thenReturn(tvShow)

        val observer = Mockito.mock(Observer::class.java) as Observer<TVShow>
        tvShowDetailViewModel?.getTVShowDetail(tvShowID).observeForever(observer)

        Mockito.verify(tvShowRepository).getDetailTVShow(tvShowID)
    }
}
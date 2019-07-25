package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito

class TVShowListViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var tvShowListViewModel: TVShowListViewModel? = null
    private var tvShowRepository: TVShowRepository = Mockito.mock(TVShowRepository::class.java)

    @Before
    fun init() {
        tvShowListViewModel = TVShowListViewModel()
        tvShowListViewModel?.tvShowRepository = tvShowRepository
    }

    @Test
    fun getTVShowList() {

        var dummyTVShows = MutableLiveData<ArrayList<TVShow>>()
        dummyTVShows.value = DataDummy.generateTVShows()

        Mockito.`when`(tvShowRepository.getListTVShow()).thenReturn(dummyTVShows)

        val observer = Mockito.mock(Observer::class.java) as Observer<ArrayList<TVShow>>
        tvShowListViewModel?.getTVShowList()?.observeForever(observer)

        Mockito.verify(tvShowRepository).getListTVShow()
    }
}
package me.alhaz.snippet.movieapp.views.tvshows.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
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
        tvShowListViewModel = TVShowListViewModel(tvShowRepository)
    }

    @Test
    fun getTVShowList() {

        val dummyTVShows = MutableLiveData<PagedList<TVShowEntity>>()
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<TVShowEntity>
        dummyTVShows.value = pagedList

        Mockito.`when`(tvShowRepository.getListTVShow()).thenReturn(dummyTVShows)

        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<TVShowEntity>>
        tvShowListViewModel?.getTVShowList()?.observeForever(observer)

        Mockito.verify(tvShowRepository).getListTVShow()

    }

}
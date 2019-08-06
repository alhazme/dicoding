package me.alhaz.snippet.movieapp.views.tvshows.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TVShowFavoriteViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var tvShowFavoriteViewModel: TVShowFavoriteViewModel? = null
    private var tvShowRepository: TVShowRepository = Mockito.mock(TVShowRepository::class.java)

    @Before
    fun init() {
        tvShowFavoriteViewModel = TVShowFavoriteViewModel(tvShowRepository)
    }

    @Test
    fun getTVShowFavorite() {

        val dummyTVShows = MutableLiveData<PagedList<TVShowEntity>>()
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<TVShowEntity>
        dummyTVShows.value = pagedList

        Mockito.`when`(tvShowRepository.getFavoriteTVShow()).thenReturn(dummyTVShows)

        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<TVShowEntity>>
        tvShowFavoriteViewModel?.getTVShowFavorite()?.observeForever(observer)

        Mockito.verify(tvShowRepository).getFavoriteTVShow()
    }

}
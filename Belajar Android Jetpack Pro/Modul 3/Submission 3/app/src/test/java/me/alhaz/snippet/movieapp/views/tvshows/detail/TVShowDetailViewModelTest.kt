package me.alhaz.snippet.movieapp.views.tvshows.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.Mockito

class TVShowDetailViewModelTest {

    private lateinit var tvShowDetailViewModel: TVShowDetailViewModel
    private var tvShowRepository: TVShowRepository = Mockito.mock(TVShowRepository::class.java)
    private var dummyTVShow: TVShowEntity = DataDummy.generateTVShowsEntity().get(0)
    private var tvShowID: Long = dummyTVShow.id

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        tvShowDetailViewModel = TVShowDetailViewModel(tvShowRepository)
    }

    @Test
    fun getTVShowDetail() {

        Mockito.`when`(tvShowRepository.getDetailTVShow(tvShowID)).thenReturn(dummyTVShow)

        val observer = Mockito.mock(Observer::class.java) as Observer<TVShowEntity>
        tvShowDetailViewModel?.getTVShowDetail(tvShowID).observeForever(observer)

        Mockito.verify(tvShowRepository).getDetailTVShow(tvShowID)

    }

    @Test
    fun setMovieFavorite() {

        dummyTVShow.favorite = 1
        Mockito.`when`(tvShowRepository.setFavorite(tvShowID)).thenReturn(dummyTVShow)

        val observer = Mockito.mock(Observer::class.java) as Observer<TVShowEntity>
        val result = tvShowDetailViewModel?.setTVShowFavorite(tvShowID)
        result.observeForever(observer)

        Mockito.verify(tvShowRepository).setFavorite(tvShowID)

        assertEquals(result.value!!.favorite, dummyTVShow.favorite)

    }

    @Test
    fun setMovieUnfavorite() {

        dummyTVShow.favorite = 0
        Mockito.`when`(tvShowRepository.setUnfavorite(tvShowID)).thenReturn(dummyTVShow)

        val observer = Mockito.mock(Observer::class.java) as Observer<TVShowEntity>
        val result = tvShowDetailViewModel?.setTVShowUnfavorite(tvShowID)
        result.observeForever(observer)

        Mockito.verify(tvShowRepository).setUnfavorite(tvShowID)

        assertEquals(result.value!!.favorite, dummyTVShow.favorite)

    }

//    @Test
//    fun getTVShowDetail() {
//
//        var tvShow = MutableLiveData<TVShow>()
//        tvShow.value = dummyTVShow
//
//        Mockito.`when`(tvShowRepository.getDetailTVShow(tvShowID)).thenReturn(tvShow)
//
//        val observer = Mockito.mock(Observer::class.java) as Observer<TVShow>
//        tvShowDetailViewModel?.getTVShowDetail(tvShowID).observeForever(observer)
//
//        Mockito.verify(tvShowRepository).getDetailTVShow(tvShowID)
//    }
}
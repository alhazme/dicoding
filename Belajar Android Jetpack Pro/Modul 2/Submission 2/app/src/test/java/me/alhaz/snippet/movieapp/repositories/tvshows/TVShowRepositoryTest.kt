package me.alhaz.snippet.movieapp.repositories.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.local.MovieLocalRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.TVShowLocalRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.TVShowRemoteRepository
import me.alhaz.snippet.movieapp.views.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TVShowRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var localRepository: TVShowLocalRepository = Mockito.mock(TVShowLocalRepository::class.java)
    private var remoteRepository: TVShowRemoteRepository = Mockito.mock(TVShowRemoteRepository::class.java)
    private var tvShowRepository: TVShowRepository? = null

    private var tvShows = DataDummy.generateTVShows()
    private var tvShow = tvShows.get(0)
    private var tvShowID = tvShow.id

    @Before
    fun init() {
        tvShowRepository = TVShowRepository()
        tvShowRepository?.tvShowLocalRepository = localRepository
        tvShowRepository?.tvShowRemoteRepository = remoteRepository
    }

    @Test
    fun getListTVShow() {

        var dummyTVShows = MutableLiveData<ArrayList<TVShow>>()
        dummyTVShows.value = tvShows

        Mockito.`when`(remoteRepository.getListTVShow()).thenReturn(dummyTVShows)
        val result = LiveDataTestUtil.getValue(tvShowRepository!!.getListTVShow())
        Mockito.verify(remoteRepository, Mockito.times(1)).getListTVShow()

        assertEquals(dummyTVShows.value?.size, result.size)

    }

    @Test
    fun getDetailTVShow() {

        var dummyTVShow = MutableLiveData<TVShow>()
        dummyTVShow.value = tvShow

        Mockito.`when`(remoteRepository.getDetailTVShow(tvShowID)).thenReturn(dummyTVShow)
        val result = LiveDataTestUtil.getValue(tvShowRepository!!.getDetailTVShow(tvShowID))
        Mockito.verify(remoteRepository, Mockito.times(1)).getDetailTVShow(tvShowID)

        assertEquals(dummyTVShow.value?.id, result.id)
        assertEquals(dummyTVShow.value?.name, result.name)
        assertEquals(dummyTVShow.value?.overview, result.overview)
    }
}
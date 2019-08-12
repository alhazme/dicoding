package me.alhaz.snippet.movieapp.repositories.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.helper.AppExecutors
import me.alhaz.snippet.movieapp.repositories.tvshows.local.TVShowLocalRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.TVShowRemoteRepository
import me.alhaz.snippet.movieapp.views.utils.InstantAppExecutors
import me.alhaz.snippet.movieapp.views.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class TVShowRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var appExecutors: AppExecutors = Mockito.mock(InstantAppExecutors::class.java)
    private var localRepository: TVShowLocalRepository = Mockito.mock(TVShowLocalRepository::class.java)
    private var remoteRepository: TVShowRemoteRepository = Mockito.mock(TVShowRemoteRepository::class.java)
    private var tvShowRepository: FakeTVShowRepository? = null

    private var tvShows = DataDummy.generateTVShows()
    private var tvShow = tvShows.get(0)
    private var tvShowID = tvShow.id

    private var tvShowEntities = DataDummy.generateTVShowsEntity()
    private var tvShowEntity = tvShowEntities.get(0)
    private var tvShowEntityID = tvShowEntity.id

    @Before
    fun init() {
        tvShowRepository = FakeTVShowRepository(appExecutors, remoteRepository, localRepository)
    }

    @Test
    fun getListTVShow() {
        val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(localRepository.getTVShowList()).thenReturn(dataSource)
        tvShowRepository!!.getListTVShow()
        verify(localRepository).getTVShowList()
    }

    @Test
    fun getDetailTVShow() {

        var dummyTVShow = MutableLiveData<TVShowEntity>()
        dummyTVShow.value = tvShowEntity

        `when`(localRepository.find(tvShowID)).thenReturn(tvShowEntity)
        val tvShow = tvShowRepository!!.getDetailTVShow(tvShowID)

        assertEquals(tvShow.id, tvShowEntity.id)
    }

    @Test
    fun getFavoriteTVShow() {
        val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        `when`(localRepository.getTVShowFavorite()).thenReturn(dataSource)
        tvShowRepository!!.getFavoriteTVShow()
        verify(localRepository).getTVShowFavorite()
    }

    @Test
    fun setFavorite() {
        tvShowEntity.favorite = 1
        `when`(localRepository.setFavorite(tvShowID)).thenReturn(tvShowEntity)
        val movie = tvShowRepository!!.setFavorite(tvShowID)

        assertEquals(movie.id, tvShowEntity.id)
        assertEquals(movie.favorite, tvShowEntity.favorite)

    }

    @Test
    fun setUnfavorite() {
        tvShowEntity.favorite = 0
        `when`(localRepository.setUnfavorite(tvShowID)).thenReturn(tvShowEntity)
        val movie = tvShowRepository!!.setUnfavorite(tvShowID)

        assertEquals(movie.id, tvShowEntity.id)
        assertEquals(movie.favorite, tvShowEntity.favorite)

    }
}
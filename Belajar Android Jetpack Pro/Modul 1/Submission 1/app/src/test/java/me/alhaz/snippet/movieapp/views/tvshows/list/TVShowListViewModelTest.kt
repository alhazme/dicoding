package me.alhaz.snippet.movieapp.views.tvshows.list

import junit.framework.Assert
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException

class TVShowListViewModelTest {

    private lateinit var tvShowListViewModel: TVShowListViewModel

    @Rule
    @JvmField
    var thrown = ExpectedException.none()

    @Before
    fun init() {
        tvShowListViewModel = TVShowListViewModel()
    }

    @Test
    fun getTVShowList() {
        tvShowListViewModel.getTVShowList()
        Assert.assertNotNull(tvShowListViewModel)
        Assert.assertEquals(20, tvShowListViewModel.tvShows.size)
    }
}
package me.alhaz.snippet.movieapp.views.tvshows.detail

import me.alhaz.snippet.movieapp.models.TVShow
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException

class TVShowDetailViewModelTest {

    private lateinit var tvShowDetailViewModel: TVShowDetailViewModel
    private lateinit var dummyTVShow: TVShow

    @Rule
    @JvmField
    var thrown = ExpectedException.none()

    @Before
    fun init() {
        tvShowDetailViewModel = TVShowDetailViewModel()
        dummyTVShow = TVShow(3,
            "Dragon Ball",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure.\n\n",
            1986,
            71,
            "25m",
            "poster_dragon_ball"
        )
    }

    @Test
    fun getTVShowDetail() {
        val movie = tvShowDetailViewModel.getTVShowDetail(dummyTVShow.id)
        assertNotNull(movie)
        movie?.let {
            assertEquals(dummyTVShow.id, it.id)
            assertEquals(dummyTVShow.title, it.title)
            assertEquals(dummyTVShow.overview, it.overview)
            assertEquals(dummyTVShow.year, it.year)
            assertEquals(dummyTVShow.score, it.score)
            assertEquals(dummyTVShow.runtime, it.runtime)
            assertEquals(dummyTVShow.photo, it.photo)
        }
    }
}
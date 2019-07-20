package me.alhaz.snippet.movieapp.views.movies.detail

import me.alhaz.snippet.movieapp.models.Movie
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException

class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private lateinit var dummyMovie: Movie

    @Rule
    @JvmField
    var thrown = ExpectedException.none()

    @Before
    fun init() {
        movieDetailViewModel = MovieDetailViewModel()
        dummyMovie = Movie(8,
            "How to Train Your Dragon: The Hidden World",
            "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            2019,
            76,
            "1h 44m",
            "poster_how_to_train"
        )
    }

    @Test
    fun getMovieDetail() {
        val movie = movieDetailViewModel.getMovieDetail(dummyMovie.id)
        assertNotNull(movie)
        movie?.let {
            assertEquals(dummyMovie.id, it.id)
            assertEquals(dummyMovie.title, it.title)
            assertEquals(dummyMovie.overview, it.overview)
            assertEquals(dummyMovie.year, it.year)
            assertEquals(dummyMovie.score, it.score)
            assertEquals(dummyMovie.runtime, it.runtime)
            assertEquals(dummyMovie.photo, it.photo)
        }
    }
}
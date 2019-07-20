package me.alhaz.snippet.movieapp.views.movies.list

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException

class MovieListViewModelTest {

    private lateinit var movieListViewModel: MovieListViewModel

    @Rule
    @JvmField
    var thrown = ExpectedException.none()

    @Before
    fun init() {
        movieListViewModel = MovieListViewModel()
    }

    @Test
    fun getMovieList() {
        movieListViewModel.getMovieList()
        assertNotNull(movieListViewModel)
        assertEquals(18, movieListViewModel.movies.size)
    }

}
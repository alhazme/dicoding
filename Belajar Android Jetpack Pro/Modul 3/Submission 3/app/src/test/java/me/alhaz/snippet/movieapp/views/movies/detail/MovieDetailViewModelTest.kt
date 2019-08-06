package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.Mockito
import org.mockito.Mockito.verify

class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private var movieRepository: MovieRepository = Mockito.mock(MovieRepository::class.java)
    private var dummyMovie: MovieEntity = DataDummy.generateListMovieEntity().get(0)
    private var movieID: Long = dummyMovie.id

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        movieDetailViewModel = MovieDetailViewModel(movieRepository)
    }

    @Test
    fun getMovieDetail() {

        Mockito.`when`(movieRepository.getDetailMovie(movieID)).thenReturn(dummyMovie)

        val observer = Mockito.mock(Observer::class.java) as Observer<MovieEntity>
        movieDetailViewModel?.getMovieDetail(movieID).observeForever(observer)

        verify(movieRepository).getDetailMovie(movieID)
    }

    @Test
    fun setMovieFavorite() {

        dummyMovie.favorite = 1
        Mockito.`when`(movieRepository.setFavorite(movieID)).thenReturn(dummyMovie)

        val observer = Mockito.mock(Observer::class.java) as Observer<MovieEntity>
        val result = movieDetailViewModel?.setMovieFavorite(movieID)
        result.observeForever(observer)

        verify(movieRepository).setFavorite(movieID)

        assertEquals(result.value!!.favorite, dummyMovie.favorite)

    }

    @Test
    fun setMovieUnfavorite() {

        dummyMovie.favorite = 0
        Mockito.`when`(movieRepository.setUnfavorite(movieID)).thenReturn(dummyMovie)

        val observer = Mockito.mock(Observer::class.java) as Observer<MovieEntity>
        val result = movieDetailViewModel?.setMovieUnfavorite(movieID)
        result.observeForever(observer)

        verify(movieRepository).setUnfavorite(movieID)

        assertEquals(result.value!!.favorite, dummyMovie.favorite)

    }
}
package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.Mockito

class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private var movieRepository: MovieRepository = Mockito.mock(MovieRepository::class.java)
    private var dummyMovie: Movie = DataDummy.generateListMovie().get(0)
    private var movieID: Long = dummyMovie.id

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        movieDetailViewModel = MovieDetailViewModel()
        movieDetailViewModel.movieRepository = movieRepository
    }

    @Test
    fun getMovieDetail() {

        var movie = MutableLiveData<Movie>()
        movie.value = dummyMovie

        Mockito.`when`(movieRepository.getDetailMovie(movieID)).thenReturn(movie)

        val observer = Mockito.mock(Observer::class.java) as Observer<Movie>
        movieDetailViewModel?.getMovieDetail(movieID).observeForever(observer)

        Mockito.verify(movieRepository).getDetailMovie(movieID)
    }
}
package me.alhaz.snippet.movieapp.views.movies.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.*

class MovieListViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var movieListViewModel: MovieListViewModel? = null
    private var movieRepository: MovieRepository = Mockito.mock(MovieRepository::class.java)

    @Before
    fun init() {
        movieListViewModel = MovieListViewModel()
        movieListViewModel?.movieRepository = movieRepository
    }

    @Test
    fun getMovieList() {

        var dummyMovies = MutableLiveData<ArrayList<Movie>>()
        dummyMovies.value = DataDummy.generateListMovie()

        `when`(movieRepository.getListMovie()).thenReturn(dummyMovies)

        val observer = Mockito.mock(Observer::class.java) as Observer<ArrayList<Movie>>
        movieListViewModel?.getMovieList()?.observeForever(observer)

        verify(movieRepository).getListMovie()
    }

}
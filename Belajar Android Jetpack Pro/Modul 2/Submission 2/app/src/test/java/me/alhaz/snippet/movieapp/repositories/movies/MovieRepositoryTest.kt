package me.alhaz.snippet.movieapp.repositories.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.local.MovieLocalRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.remote.MovieRemoteRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import me.alhaz.snippet.movieapp.views.utils.LiveDataTestUtil
import org.mockito.Mockito.*


class MovieRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var localRepository: MovieLocalRepository = Mockito.mock(MovieLocalRepository::class.java)
    private var remoteRepository: MovieRemoteRepository = Mockito.mock(MovieRemoteRepository::class.java)
    private var movieRepository: MovieRepository? = null

    private var movies = DataDummy.generateListMovie()
    private var movie = movies.get(0)
    private var movieID = movie.id

    @Before
    fun init() {
        movieRepository = MovieRepository()
        movieRepository?.movieLocalRepository = localRepository
        movieRepository?.movieRemoteRepository = remoteRepository
    }

    @Test
    fun getListMovie() {

        var dummyMovies = MutableLiveData<ArrayList<Movie>>()
        dummyMovies.value = movies

        `when`(remoteRepository.getListMovie()).thenReturn(dummyMovies)
        val result = LiveDataTestUtil.getValue(movieRepository!!.getListMovie())
        verify(remoteRepository, times(1)).getListMovie()

        assertEquals(dummyMovies.value?.size, result.size)
    }

    @Test
    fun getDetailMovie() {

        var dummyMovie = MutableLiveData<Movie>()
        dummyMovie.value = DataDummy.generateListMovie().get(0)

        `when`(remoteRepository.getDetailMovie(movieID)).thenReturn(dummyMovie)
        val result = LiveDataTestUtil.getValue(movieRepository!!.getDetailMovie(movieID))
        verify(remoteRepository, times(1)).getDetailMovie(movieID)

        assertEquals(dummyMovie.value?.id, result.id)
        assertEquals(dummyMovie.value?.title, result.title)
        assertEquals(dummyMovie.value?.overview, result.overview)
    }

}
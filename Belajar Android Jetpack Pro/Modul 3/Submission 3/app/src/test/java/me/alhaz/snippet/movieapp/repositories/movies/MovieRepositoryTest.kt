package me.alhaz.snippet.movieapp.repositories.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.local.MovieLocalRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.repositories.movies.remote.MovieRemoteRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import me.alhaz.snippet.movieapp.views.utils.LiveDataTestUtil
import org.mockito.ArgumentMatchers
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

    private var movieEntities = DataDummy.generateListMovieEntity()
    private var movieEntity = movieEntities.get(0)
    private var movieEntityID = movieEntity.id

    @Before
    fun init() {
        movieRepository = MovieRepository(remoteRepository, localRepository)
    }

    @Test
    fun getListMovieFromServer() {

        var dummyMovies = MutableLiveData<ArrayList<Movie>>()
        dummyMovies.value = movies

        `when`(remoteRepository.getListMovie()).thenReturn(dummyMovies)
        val result = LiveDataTestUtil.getValue(movieRepository!!.getListMovieFromServer())
        verify(remoteRepository, times(1)).getListMovie()

        assertEquals(dummyMovies.value?.size, result.size)

    }

    @Test
    fun getListMovie() {
        val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localRepository.getMovieList()).thenReturn(dataSource)
        movieRepository!!.getListMovie()
        verify(localRepository).getMovieList()
    }

    @Test
    fun getDetailMovie() {

        var dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movieEntity

        `when`(localRepository.find(movieID)).thenReturn(movieEntity)
        val movie = movieRepository!!.getDetailMovie(movieID)

        assertEquals(movie.id, movieEntity.id)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localRepository.getMovieFavorites()).thenReturn(dataSource)
        movieRepository!!.getFavoriteMovies()
        verify(localRepository).getMovieFavorites()
    }

    @Test
    fun setFavorite() {
        movieEntity.favorite = 1
        `when`(localRepository.setFavorite(movieID)).thenReturn(movieEntity)
        val movie = movieRepository!!.setFavorite(movieID)

        assertEquals(movie.id, movieEntity.id)
        assertEquals(movie.favorite, movieEntity.favorite)

    }

    @Test
    fun setUnfavorite() {
        movieEntity.favorite = 0
        `when`(localRepository.setUnfavorite(movieID)).thenReturn(movieEntity)
        val movie = movieRepository!!.setUnfavorite(movieID)

        assertEquals(movie.id, movieEntity.id)
        assertEquals(movie.favorite, movieEntity.favorite)

    }

}
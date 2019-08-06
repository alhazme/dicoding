package me.alhaz.snippet.movieapp.views.movies.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieFavoriteViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var movieFavoriteViewModel: MovieFavoriteViewModel? = null
    private var movieRepository: MovieRepository = Mockito.mock(MovieRepository::class.java)

    @Before
    fun init() {
        movieFavoriteViewModel = MovieFavoriteViewModel(movieRepository)
    }

    @Test
    fun getMovieFavorites() {

        val dummyMovies = MutableLiveData<PagedList<MovieEntity>>()
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<MovieEntity>
        dummyMovies.value = pagedList

        Mockito.`when`(movieRepository.getFavoriteMovies()).thenReturn(dummyMovies)

        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<MovieEntity>>
        movieFavoriteViewModel?.getMovieFavorites()?.observeForever(observer)

        Mockito.verify(movieRepository).getFavoriteMovies()
    }
}
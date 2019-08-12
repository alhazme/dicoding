package me.alhaz.snippet.movieapp.views.movies.list

import android.graphics.pdf.PdfDocument
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.data.DataDummy
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.valueobject.Resource
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
        movieListViewModel = MovieListViewModel(movieRepository)
    }

    @Test
    fun getMovieList() {

        val dummyMovies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<MovieEntity>
        dummyMovies.value = Resource.success(pagedList)

        `when`(movieRepository.getListMovie()).thenReturn(dummyMovies)

        val observer = Mockito.mock(Observer::class.java) as Observer<Resource<PagedList<MovieEntity>>>
        movieListViewModel?.getListMovie()?.observeForever(observer)

        verify(movieRepository).getListMovie()

    }

}
package me.alhaz.snippet.movieapp.repositories.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.helper.AppExecutors
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.NetworkBoundResource
import me.alhaz.snippet.movieapp.repositories.movies.local.MovieLocalRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.repositories.movies.remote.MovieRemoteRepository
import me.alhaz.snippet.movieapp.valueobject.ApiResponse
import me.alhaz.snippet.movieapp.valueobject.Resource

class MovieRepository(private val appExecutors: AppExecutors, private val movieRemoteRepository: MovieRemoteRepository, private val movieLocalRepository: MovieLocalRepository): MovieDataSource {

    override fun getListMovie(): LiveData<Resource<PagedList<MovieEntity>>> {

        return object: NetworkBoundResource<PagedList<MovieEntity>, ArrayList<Movie>>(appExecutors) {

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return (movieLocalRepository.counts() == 0)
            }

            override fun createCall(): LiveData<ApiResponse<ArrayList<Movie>>> {
                return movieRemoteRepository.getListMovie()
            }

            override fun saveCallResult(item: ArrayList<Movie>) {
                item.forEach { movie ->
                    val movieEntity = MovieEntity(
                        id = movie.id,
                        title = movie.title,
                        voteAverage = movie.voteAverage,
                        overview = movie.overview,
                        releaseDate = movie.releaseDate,
                        runtime = movie.runtime,
                        posterPath = movie.posterPath
                    )
                    movieLocalRepository.insert(movieEntity)
                }
            }

            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(movieLocalRepository.getMovieList(), 10).build()
            }

        }.asLiveData()
    }

    override fun getDetailMovie(movieID: Long): MovieEntity {
        return movieLocalRepository.find(movieID)
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(movieLocalRepository.getMovieFavorites(), 10).build()
    }

    override fun setFavorite(movieID: Long) : MovieEntity {
        return movieLocalRepository.setFavorite(movieID)
    }

    override fun setUnfavorite(movieID: Long) : MovieEntity {
        return movieLocalRepository.setUnfavorite(movieID)
    }

}
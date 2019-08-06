package me.alhaz.snippet.movieapp.repositories.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.movies.local.MovieLocalRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.repositories.movies.remote.MovieRemoteRepository

class MovieRepository(movieRemoteRepository: MovieRemoteRepository, movieLocalRepository: MovieLocalRepository): MovieDataSource {

    var movieRemoteRepository: MovieRemoteRepository
    var movieLocalRepository: MovieLocalRepository

    init {
        this.movieRemoteRepository = movieRemoteRepository
        this.movieLocalRepository = movieLocalRepository
    }

    override fun getListMovieFromServer(): MutableLiveData<ArrayList<Movie>> {
        var movieLiveData = MutableLiveData<ArrayList<Movie>>()
        movieRemoteRepository.let { remoteRepository ->
            val listMovie = remoteRepository.getListMovie()
            movieLocalRepository.let { localRepository ->
                listMovie.value?.let { movies ->
                    movieLiveData.value = movies
                    movies.forEach { movie ->
                        val movieEntity = MovieEntity(
                            id = movie.id,
                            title = movie.title,
                            voteAverage = movie.voteAverage,
                            overview = movie.overview,
                            releaseDate = movie.releaseDate,
                            runtime = movie.runtime,
                            posterPath = movie.posterPath
                        )
                        localRepository.insert(movieEntity)
                    }
                }
            }
        }
        return movieLiveData
    }

    override fun getListMovie(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(movieLocalRepository.getMovieList(), 10).build()
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
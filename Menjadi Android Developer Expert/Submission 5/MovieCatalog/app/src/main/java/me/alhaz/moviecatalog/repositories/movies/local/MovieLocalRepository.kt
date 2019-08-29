package me.alhaz.moviecatalog.repositories.movies.local

import android.app.Application
import androidx.paging.DataSource
import me.alhaz.moviecatalog.database.MovieAppDatabase
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntityDAO
import me.alhaz.snippet.movieapp.models.Movie

class MovieLocalRepository(application: Application) {

    private var movieEntityDao: MovieEntityDAO

    init {
        val db = MovieAppDatabase.getInstance(application)
        movieEntityDao = db.movieDao()
    }

    fun getMovieList(): DataSource.Factory<Int, MovieEntity> {
        return movieEntityDao.getMovieList()
    }

    fun find(id: Long): MovieEntity {
        return movieEntityDao.find(id)
    }

    fun counts(): Int {
        return movieEntityDao.counts()
    }

    fun insert(movie: MovieEntity) {
        movieEntityDao.insert(movie)
    }

    fun deleteAll() {
        movieEntityDao.deleteAll()
    }

    fun getMovieFavorites(): DataSource.Factory<Int, MovieEntity> {
        return movieEntityDao.getMovieFavorites()
    }

    fun setFavorite(movie: Movie) : MovieEntity {
        val movieEntity = MovieEntity(
            id = movie.id,
            title = movie.title,
            voteAverage = movie.voteAverage,
            overview = movie.overview,
            releaseDate = movie.releaseDate,
            runtime = movie.runtime,
            posterPath = movie.posterPath,
            favorite = 1
        )
        movieEntityDao.insert(movieEntity)
        return movieEntityDao.find(movie.id)
    }

    fun setUnfavorite(movie: Movie) : MovieEntity {
        movieEntityDao.delete(movie.id)
        return movieEntityDao.find(movie.id)
    }

    fun searchMovie(title: String) : DataSource.Factory<Int, MovieEntity> {
        return movieEntityDao.searchMovie(title)
    }

    fun getMoviePosterFavorites(): List<MovieEntity> {
        return movieEntityDao.getMoviePosterFavorites()
    }

}
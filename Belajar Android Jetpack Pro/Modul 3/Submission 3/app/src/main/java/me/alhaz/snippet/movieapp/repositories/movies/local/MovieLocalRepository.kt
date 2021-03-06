package me.alhaz.snippet.movieapp.repositories.movies.local

import android.app.Application
import androidx.paging.DataSource
import me.alhaz.snippet.movieapp.database.MovieAppDatabase
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntityDAO

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

    fun setFavorite(id: Long) : MovieEntity {
        movieEntityDao.setFavorite(id)
        return movieEntityDao.find(id)
    }

    fun setUnfavorite(id: Long) : MovieEntity {
        movieEntityDao.setUnfavorite(id)
        return movieEntityDao.find(id)
    }

}
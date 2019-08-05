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

    fun setFavorite(id: Long) {
        movieEntityDao.setFavorite(id)
    }

    fun setUnfavorite(id: Long) {
        movieEntityDao.setUnfavorite(id)
    }

}
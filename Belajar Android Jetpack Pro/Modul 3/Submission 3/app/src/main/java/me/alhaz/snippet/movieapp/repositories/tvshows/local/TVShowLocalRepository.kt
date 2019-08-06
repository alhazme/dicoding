package me.alhaz.snippet.movieapp.repositories.tvshows.local

import android.app.Application
import androidx.paging.DataSource
import me.alhaz.snippet.movieapp.database.MovieAppDatabase
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntityDAO

class TVShowLocalRepository(application: Application) {

    private var tvShowEntityDao: TVShowEntityDAO

    init {
        val db = MovieAppDatabase.getInstance(application)
        tvShowEntityDao = db.tvShowDao()
    }

    fun getTVShowList(): DataSource.Factory<Int, TVShowEntity> {
        return tvShowEntityDao.getTVShowList()
    }

    fun find(id: Long): TVShowEntity {
        return tvShowEntityDao.find(id)
    }

    fun counts(): Int {
        return tvShowEntityDao.counts()
    }

    fun insert(movie: TVShowEntity) {
        tvShowEntityDao.insert(movie)
    }

    fun deleteAll() {
        tvShowEntityDao.deleteAll()
    }

    fun getTVShowFavorite(): DataSource.Factory<Int, TVShowEntity> {
        return tvShowEntityDao.getTVShowFavorite()
    }

    fun setFavorite(id: Long) : TVShowEntity  {
        tvShowEntityDao.setFavorite(id)
        return tvShowEntityDao.find(id)
    }

    fun setUnfavorite(id: Long) : TVShowEntity {
        tvShowEntityDao.setUnfavorite(id)
        return tvShowEntityDao.find(id)
    }

}
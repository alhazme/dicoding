package me.alhaz.snippet.movieapp.repositories.tvshows.local

import android.app.Application
import androidx.paging.DataSource
import me.alhaz.snippet.movieapp.database.MovieAppDatabase
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntityDAO

class TVShowLocalRepository(application: Application) {

    private var tvShowEntityDao: TVShowEntityDAO

    init {
        val db = MovieAppDatabase.getInstance(application)
        tvShowEntityDao = db.tvShowDao()
    }

    fun getTVShowList(): DataSource.Factory<Integer, TVShowEntity> {
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

    fun setFavorite(id: Long) {
        tvShowEntityDao.setFavorite(id)
    }

    fun setUnfavorite(id: Long) {
        tvShowEntityDao.setUnfavorite(id)
    }

}
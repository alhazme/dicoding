package me.alhaz.moviecatalog.repositories.tvshows.local

import android.app.Application
import androidx.paging.DataSource
import me.alhaz.moviecatalog.database.MovieAppDatabase
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntityDAO

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
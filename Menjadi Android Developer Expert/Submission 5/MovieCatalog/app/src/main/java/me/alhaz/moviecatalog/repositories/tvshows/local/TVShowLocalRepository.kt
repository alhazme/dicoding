package me.alhaz.moviecatalog.repositories.tvshows.local

import android.app.Application
import androidx.paging.DataSource
import me.alhaz.moviecatalog.database.MovieAppDatabase
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
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

    fun setFavorite(tvShow: TVShow) : TVShowEntity  {
        val tvShowEntity = TVShowEntity(
            id = tvShow.id,
            name = tvShow.name,
            voteAverage = tvShow.voteAverage,
            overview = tvShow.overview,
            overview_id = tvShow.overview_id,
            firstAirDate = tvShow.firstAirDate,
            numberOfEpisodes = tvShow.numberOfEpisodes,
            posterPath = tvShow.posterPath,
            favorite = 1
        )
        tvShowEntityDao.insert(tvShowEntity)
        return tvShowEntityDao.find(tvShow.id)
    }

    fun setUnfavorite(tvShow: TVShow) : TVShowEntity {
        tvShowEntityDao.delete(tvShow.id)
        return tvShowEntityDao.find(tvShow.id)
    }

    fun searchTVShow(name: String) : DataSource.Factory<Int, TVShowEntity> {
        return tvShowEntityDao.searchTVShow(name)
    }

    fun getTVShowPosterFavorites(): List<TVShowEntity> {
        return tvShowEntityDao.getTVShowPosterFavorites()
    }

}
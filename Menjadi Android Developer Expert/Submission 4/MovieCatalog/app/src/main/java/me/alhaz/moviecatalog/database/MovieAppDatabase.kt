package me.alhaz.moviecatalog.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntityDAO
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntityDAO

@Database(entities = arrayOf(MovieEntity::class, TVShowEntity::class), version = 1, exportSchema = false)
abstract class MovieAppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieEntityDAO
    abstract fun tvShowDao(): TVShowEntityDAO
    // add other Daos here

    companion object {

        private const val DB_NAME = "movieapp.db"
        private var INSTANCE: MovieAppDatabase? = null

        fun getInstance(application: Application): MovieAppDatabase {
            if (INSTANCE == null) {
                synchronized(MovieAppDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(application.applicationContext, MovieAppDatabase::class.java, DB_NAME).allowMainThreadQueries().build()
                    }
                }
            }
            return INSTANCE!!
        }

        fun dropInstance() {
            INSTANCE = null
        }

    }

}
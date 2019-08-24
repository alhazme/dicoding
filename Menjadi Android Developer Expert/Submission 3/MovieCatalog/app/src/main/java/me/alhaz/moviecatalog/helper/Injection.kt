package me.alhaz.moviecatalog.helper

import android.app.Application
import me.alhaz.moviecatalog.repositories.movies.MovieRepository
import me.alhaz.moviecatalog.repositories.movies.remote.MovieRemoteRepository
import me.alhaz.moviecatalog.repositories.tvshows.TVShowRepository
import me.alhaz.moviecatalog.repositories.tvshows.remote.TVShowRemoteRepository

class Injection {

    companion object {

        fun provideMovieRepository(application: Application): MovieRepository {
//            val appExecutors = AppExecutors()
            val movieRemoteRepository = MovieRemoteRepository()
//            val movieLocalRepository = MovieLocalRepository(application)
//            val movieRepository = MovieRepository(appExecutors, movieRemoteRepository, movieLocalRepository)
            val movieRepository = MovieRepository(movieRemoteRepository)
            return movieRepository
        }

        fun provideTVShowRepository(application: Application): TVShowRepository {
//            val appExecutors = AppExecutors()
            val tvShowRemoteRepository = TVShowRemoteRepository()
//            val tvShowLocalRepository = TVShowLocalRepository(application)
//            val tvShowRepository = TVShowRepository(appExecutors, tvShowRemoteRepository, tvShowLocalRepository)
            val tvShowRepository = TVShowRepository(tvShowRemoteRepository)
            return tvShowRepository
        }
    }

}

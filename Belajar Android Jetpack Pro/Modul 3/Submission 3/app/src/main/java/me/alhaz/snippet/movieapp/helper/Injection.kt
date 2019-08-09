package me.alhaz.snippet.movieapp.helper

import android.app.Application
import me.alhaz.snippet.movieapp.repositories.movies.MovieRepository
import me.alhaz.snippet.movieapp.repositories.movies.local.MovieLocalRepository
import me.alhaz.snippet.movieapp.repositories.movies.remote.MovieRemoteRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.local.TVShowLocalRepository
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.TVShowRemoteRepository

class Injection {

    companion object {

        fun provideMovieRepository(application: Application): MovieRepository {
            val appExecutors = AppExecutors()
            val movieRemoteRepository = MovieRemoteRepository()
            val movieLocalRepository = MovieLocalRepository(application)
            val movieRepository = MovieRepository(appExecutors, movieRemoteRepository, movieLocalRepository)
            return movieRepository
        }

        fun provideTVShowRepository(application: Application): TVShowRepository {
            val appExecutors = AppExecutors()
            val tvShowRemoteRepository = TVShowRemoteRepository()
            val tvShowLocalRepository = TVShowLocalRepository(application)
            val tvShowRepository = TVShowRepository(tvShowRemoteRepository, tvShowLocalRepository)
            return tvShowRepository
        }
    }

}
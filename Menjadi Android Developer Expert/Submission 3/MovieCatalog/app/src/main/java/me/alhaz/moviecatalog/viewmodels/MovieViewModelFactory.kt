package me.alhaz.moviecatalog.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alhaz.moviecatalog.helper.Injection
import me.alhaz.moviecatalog.repositories.movies.MovieRepository
import me.alhaz.moviecatalog.views.movies.detail.MovieDetailViewModel
import me.alhaz.moviecatalog.views.movies.list.MovieListViewModel

class MovieViewModelFactory private constructor(private val movieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @Volatile
        private var INSTANCE: MovieViewModelFactory? = null

        fun getInstance(application: Application): MovieViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(MovieViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            MovieViewModelFactory(
                                Injection.provideMovieRepository(application)
                            )
                    }
                }
            }
            return INSTANCE
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(movieRepository) as T
        }
        else if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(movieRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}
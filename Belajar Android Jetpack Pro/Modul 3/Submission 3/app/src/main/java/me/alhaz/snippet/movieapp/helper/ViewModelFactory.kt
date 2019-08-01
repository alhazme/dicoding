package me.alhaz.snippet.movieapp.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alhaz.snippet.movieapp.views.movies.detail.MovieDetailViewModel
import me.alhaz.snippet.movieapp.views.movies.list.MovieListViewModel
import me.alhaz.snippet.movieapp.views.tvshows.detail.TVShowDetailViewModel
import me.alhaz.snippet.movieapp.views.tvshows.list.TVShowListViewModel

class ViewModelFactory private constructor(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {

            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(application)
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(application) as T
        }
        else if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            return MovieDetailViewModel(application) as T
        }
        else if (modelClass.isAssignableFrom(TVShowListViewModel::class.java)) {
            return TVShowListViewModel(application) as T
        }
        else if (modelClass.isAssignableFrom(TVShowDetailViewModel::class.java)) {
            return TVShowDetailViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
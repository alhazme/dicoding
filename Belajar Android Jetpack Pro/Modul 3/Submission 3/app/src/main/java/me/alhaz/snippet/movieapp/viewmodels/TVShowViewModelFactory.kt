package me.alhaz.snippet.movieapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.alhaz.snippet.movieapp.helper.Injection
import me.alhaz.snippet.movieapp.repositories.tvshows.TVShowRepository
import me.alhaz.snippet.movieapp.views.tvshows.detail.TVShowDetailViewModel
import me.alhaz.snippet.movieapp.views.tvshows.favorite.TVShowFavoriteViewModel
import me.alhaz.snippet.movieapp.views.tvshows.list.TVShowListViewModel

class TVShowViewModelFactory private constructor(private val tvShowRepository: TVShowRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @Volatile
        private var INSTANCE: TVShowViewModelFactory? = null

        fun getInstance(application: Application): TVShowViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(TVShowViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            TVShowViewModelFactory(
                                Injection.provideTVShowRepository(application)
                            )
                    }
                }
            }
            return INSTANCE
        }

    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TVShowListViewModel::class.java)) {
            return TVShowListViewModel(tvShowRepository) as T
        }
        else if (modelClass.isAssignableFrom(TVShowDetailViewModel::class.java)) {
            return TVShowDetailViewModel(tvShowRepository) as T
        }
        else if (modelClass.isAssignableFrom(TVShowFavoriteViewModel::class.java)) {
            return TVShowFavoriteViewModel(tvShowRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
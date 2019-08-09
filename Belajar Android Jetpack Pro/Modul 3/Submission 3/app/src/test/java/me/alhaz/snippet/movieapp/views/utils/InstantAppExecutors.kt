package me.alhaz.snippet.movieapp.views.utils

import me.alhaz.snippet.movieapp.helper.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {

    companion object {
        private val instant = Executor {
            it.run()
        }
    }

}
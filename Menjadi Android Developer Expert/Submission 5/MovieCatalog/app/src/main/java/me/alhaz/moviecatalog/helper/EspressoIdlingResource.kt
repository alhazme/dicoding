package me.alhaz.moviecatalog.helper

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource {

    companion object {

        private val RESOURCE = "GLOBAL"
        private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            espressoTestIdlingResource.increment()
        }

        fun decrement() {
            espressoTestIdlingResource.decrement()
        }

        fun getEspressoIdlingResourceForMainActivity(): IdlingResource {
            return espressoTestIdlingResource
        }

    }

}
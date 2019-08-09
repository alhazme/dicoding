package me.alhaz.snippet.movieapp.valueobject

import me.alhaz.snippet.movieapp.valueobject.Status.SUCCESS
import me.alhaz.snippet.movieapp.valueobject.Status.ERROR
import me.alhaz.snippet.movieapp.valueobject.Status.LOADING
import me.alhaz.snippet.movieapp.valueobject.Status.EMPTY

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }

        fun <T> empty(data: T?): Resource<T> {
            return Resource(EMPTY, data, null)
        }
    }
}
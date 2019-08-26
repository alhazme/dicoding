package me.alhaz.moviecatalog.valueobject

import me.alhaz.moviecatalog.valueobject.Status.SUCCESS
import me.alhaz.moviecatalog.valueobject.Status.ERROR
import me.alhaz.moviecatalog.valueobject.Status.EMPTY

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> empty(data: T?): Resource<T> {
            return Resource(EMPTY, data, null)
        }
    }
}
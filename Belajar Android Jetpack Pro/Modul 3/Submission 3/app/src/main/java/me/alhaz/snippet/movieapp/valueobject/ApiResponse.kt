package me.alhaz.snippet.movieapp.valueobject

import androidx.annotation.Nullable
import me.alhaz.snippet.movieapp.valueobject.Status.SUCCESS
import me.alhaz.snippet.movieapp.valueobject.Status.ERROR
import me.alhaz.snippet.movieapp.valueobject.Status.EMPTY

class ApiResponse<T> (val status: Status, val body: T, val message: String) {

    companion object {

        fun <T> success(@Nullable body: T): ApiResponse<T> {
            return ApiResponse(SUCCESS, body, "")
        }

        fun <T> error(msg: String, @Nullable body: T): ApiResponse<T> {
            return ApiResponse(ERROR, body, msg)
        }

        fun <T> empty(msg: String, @Nullable body: T): ApiResponse<T> {
            return ApiResponse(EMPTY, body, msg)
        }

    }


}
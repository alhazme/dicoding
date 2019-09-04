package me.alhaz.movieconsumer.data

import android.net.Uri

class DataConnector {

    companion object {

        val SCHEME = "content"
        val AUTHORITY = "me.alhaz.moviecatalog.provider.MovieContentProvider"
        val TABLE_NAME = "movie"

        @JvmStatic
        fun getContentURI() : Uri {
            return Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }

        @JvmStatic
        fun getContentURIDetail(id: Long) : Uri {
            return Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .appendPath(id.toString())
                .build()
        }
    }

}
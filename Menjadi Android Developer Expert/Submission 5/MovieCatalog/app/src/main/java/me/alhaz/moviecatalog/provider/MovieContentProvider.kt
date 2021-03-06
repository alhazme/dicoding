package me.alhaz.moviecatalog.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.content.UriMatcher
import android.util.Log
import me.alhaz.moviecatalog.database.MovieAppDatabase


class MovieContentProvider: ContentProvider() {

    companion object {
        val AUTHORITY = "me.alhaz.moviecatalog.provider.MovieContentProvider"
        private val MOVIE_TABLE = "movie"
        val CONTENT_URI : Uri = Uri.parse("content://" + AUTHORITY + "/" + MOVIE_TABLE)
        val REQUEST_CODE_LIST = 0
        val REQUEST_CODE_DETAIL = 1
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)
    }

    override fun onCreate(): Boolean {
        MATCHER.addURI(AUTHORITY, MOVIE_TABLE, REQUEST_CODE_LIST)
        MATCHER.addURI(AUTHORITY, MOVIE_TABLE + "/*", REQUEST_CODE_DETAIL)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d("1234567890", "uri: " + uri.toString())
        val code = MATCHER.match(uri)
        Log.d("1234567890", "Code MATCHER.match(uri): " + code)
        if (code == REQUEST_CODE_LIST || code == REQUEST_CODE_DETAIL) {
            if (context == null) return null
            val db = MovieAppDatabase.getInstance(context)
            val movieDAO = db.movieDao()
            var cursor: Cursor
            if (code == REQUEST_CODE_DETAIL) {
                Log.d("1234567890", "Content Provider: REQUEST_CODE_DETAIL")
                cursor = movieDAO.getMovieFavoriteDetailForContentProvider(ContentUris.parseId(uri))
                cursor.setNotificationUri(context.getContentResolver(), uri)
            }
            else {
                Log.d("1234567890", "Content Provider: REQUEST_CODE_LIST")
                cursor = movieDAO.getMovieFavoriteForContentProvider()
                cursor.setNotificationUri(context.getContentResolver(), uri)
            }
            return cursor
        }
        else {
            throw IllegalArgumentException("Unknown URI: " + uri)
        }
    }

    override fun getType(uri: Uri): String? {
        when (MATCHER.match(uri)) {
            REQUEST_CODE_LIST -> {
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + "movie"
            }
            REQUEST_CODE_DETAIL -> {
                return "vnd.android.cursor.item/" + AUTHORITY + "." + "movie"
            }
        }
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

}
package me.alhaz.movieconsumer.data

import android.os.Handler
import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import me.alhaz.movieconsumer.views.movies.list.MainActivity


class DataObserver(var handler: Handler, val context: Context) : ContentObserver(handler) {

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        GetData(context, context as MainActivity).execute()
    }
}

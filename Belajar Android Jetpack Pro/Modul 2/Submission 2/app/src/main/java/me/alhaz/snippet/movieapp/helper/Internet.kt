package me.alhaz.snippet.movieapp.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Internet {

    fun connectionAvailable(context: Context):Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo =  connectivityManager.activeNetworkInfo
        var isConnected: Boolean = false
        activeNetwork?.let {
            isConnected = it.isConnected == true
        }
        return isConnected
    }

}
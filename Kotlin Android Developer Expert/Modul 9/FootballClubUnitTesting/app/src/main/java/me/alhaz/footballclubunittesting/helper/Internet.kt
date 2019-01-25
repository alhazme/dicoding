package me.alhaz.footballclubunittesting.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity

class Internet {

    fun connectionAvailable(context: Context):Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        var isConnected: Boolean = false
        activeNetwork?.let {
            isConnected = it.isConnected == true
        }
        return isConnected
    }

}
package me.alhaz.snippet.movieapp.views.utils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


object LiveDataTestUtil {

    fun <T> getValue(mutableLiveData: MutableLiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(o: T) {
                data[0] = o
                latch.countDown()
                mutableLiveData.removeObserver(this)
            }
        }

        mutableLiveData.observeForever(observer)

        try {
            latch.await(2, TimeUnit.SECONDS)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return data[0] as T

    }
}
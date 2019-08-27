package me.alhaz.moviecatalog.helper

import me.alhaz.moviecatalog.BuildConfig
import me.alhaz.moviecatalog.repositories.movies.remote.MovieAPIService
import me.alhaz.moviecatalog.repositories.tvshows.remote.TVShowAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitConfig {

    fun config(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getMovieService(): MovieAPIService {
        return config().create(MovieAPIService::class.java)
    }

    fun getTVShowService(): TVShowAPIService {
        return config().create(TVShowAPIService::class.java)
    }

}
package me.alhaz.snippet.movieapp.helper

import me.alhaz.snippet.movieapp.BuildConfig
import me.alhaz.snippet.movieapp.repositories.movies.remote.MovieAPIService
import me.alhaz.snippet.movieapp.repositories.tvshows.remote.TVShowAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
            .baseUrl(BuildConfig.DUMMY_URL)
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
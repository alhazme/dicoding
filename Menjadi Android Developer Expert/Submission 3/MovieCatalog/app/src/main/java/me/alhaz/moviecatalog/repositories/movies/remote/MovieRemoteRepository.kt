package me.alhaz.moviecatalog.repositories.movies.remote

import androidx.lifecycle.MutableLiveData
import me.alhaz.moviecatalog.BuildConfig
import me.alhaz.moviecatalog.helper.RetrofitConfig
import me.alhaz.moviecatalog.repositories.movies.remote.response.MoviePopularResponse
import me.alhaz.snippet.movieapp.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteRepository {

    fun getListMovie(): MutableLiveData<ArrayList<Movie>> {

        val movies = MutableLiveData<ArrayList<Movie>>()

        RetrofitConfig().getMovieService().getListMovie(BuildConfig.API_KEY).enqueue(object: Callback<MoviePopularResponse> {

            override fun onResponse(call: Call<MoviePopularResponse>, response: Response<MoviePopularResponse>) {
                if (response.isSuccessful) {
                    val movieResponses = ArrayList<Movie>()
                    val responseData = response.body()
                    responseData?.let {
                        val moviePopularResponse: MoviePopularResponse = it
                        moviePopularResponse.results?.let {
                            val data: List<Movie> = it
                            if (data.isNotEmpty()) {
                                data.forEach {
                                    movieResponses.add(it)
                                }
                            }
                        }
                    }
                    movies.postValue(movieResponses)
                }
            }

            override fun onFailure(call: Call<MoviePopularResponse>, t: Throwable) {

            }
        })

        return movies
    }

    fun getDetailMovie(movieID: Long): MutableLiveData<Movie> {

        var movie = MutableLiveData<Movie>()

        RetrofitConfig().getMovieService().getDetailMovie(movieID, BuildConfig.API_KEY).enqueue(object: Callback<Movie> {

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let {
                        movie.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return movie
    }

}
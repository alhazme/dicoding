package me.alhaz.moviecatalog.repositories.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.models.Movie

interface MovieDataSource {

    fun getListMovie(): MutableLiveData<ArrayList<Movie>>

    fun getDetailMovie(movieID: Long): MutableLiveData<Movie>

}
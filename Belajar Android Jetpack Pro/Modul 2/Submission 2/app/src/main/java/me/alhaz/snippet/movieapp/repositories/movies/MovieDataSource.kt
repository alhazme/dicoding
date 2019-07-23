package me.alhaz.snippet.movieapp.repositories.movies

import androidx.lifecycle.MutableLiveData
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie

interface MovieDataSource {

    fun getListMovie(): MutableLiveData<ArrayList<Movie>>

    fun getDetailMovie(movieID: Long): MutableLiveData<Movie>

}
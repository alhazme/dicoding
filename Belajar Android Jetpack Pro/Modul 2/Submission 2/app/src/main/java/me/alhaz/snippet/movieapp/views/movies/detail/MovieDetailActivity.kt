package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import kotlin.math.ceil

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    var movieID: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        getIntentData()
        setupViewModel()
        setupLayout()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getIntentData() {
        intent.extras?.let {
            movieID = it.getLong("movie_id", 0)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        viewModel.getMovieDetail(movieID)
        viewModel.getMovie().observe(this, Observer {
            showDetailData(it)
            ly_loading.setVisibility(View.GONE)
            ly_content.setVisibility(View.VISIBLE)
        })
    }

    private fun setupLayout() {
        title = "Detail"
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        ly_loading.setVisibility(View.VISIBLE)
        ly_content.setVisibility(View.GONE)
    }

    private fun showDetailData(movie: Movie) {
        Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.posterPath).into(iv_photo)
        tv_year.text = movie.releaseDate.split("-").get(0)
        tv_title.text = movie.title
        tv_rating.text = movie.voteAverage.toString()
        tv_runtime.text = getRuntime(movie.runtime)
        tv_description.text = movie.overview
    }

    private fun getRuntime(runtime: Long): String {
        val hour = (runtime / 60).toString().split(".").get(0)
        val minute = runtime.rem(60)
        return "${hour}h ${minute}m"
    }

}

package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.models.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    var movieID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupViewModel()
        getIntentData()
        showDetailData(movieID)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java);
    }

    private fun getIntentData() {
        intent.extras?.let {
            movieID = it.getInt("movie_id", 0)
        }
    }

    fun showDetailData(movieID: Int) {
        title = "Detail"
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        val movie: Movie? = viewModel.getMovieDetail(movieID)
        movie?.let {
            val imageId = resources.getIdentifier(movie.photo, "drawable", packageName)
            iv_photo.setImageResource(imageId)
            tv_year.text = movie.year.toString()
            tv_title.text = movie.title
            tv_rating.text = movie.score.toString()
            tv_runtime.text = movie.runtime
            tv_description.text = movie.overview
        }
    }

}

package me.alhaz.moviecatalog.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.moviecatalog.R
import me.alhaz.snippet.movieapp.models.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        getIntentData()
        showDetailData(movie)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getIntentData() {
        intent.extras?.let { bundle ->
            movie = bundle.getParcelable("movie") as Movie
        }
    }

    private fun showDetailData(movie: Movie) {
        title = "Detail"
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        val imageId = resources.getIdentifier(movie.photo, "drawable", packageName)
        iv_photo.setImageResource(imageId)
        tv_year.text = movie.year.toString()
        tv_title.text = movie.title
        tv_rating.text = movie.score.toString()
        tv_runtime.text = movie.runtime
        tv_description.text = movie.overview
    }


}

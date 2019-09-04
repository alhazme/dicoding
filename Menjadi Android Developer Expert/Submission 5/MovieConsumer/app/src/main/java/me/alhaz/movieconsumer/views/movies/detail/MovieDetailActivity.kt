package me.alhaz.movieconsumer.views.movies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.movieconsumer.R
import me.alhaz.movieconsumer.models.Movie

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        getIntentData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getIntentData() {
        intent.extras?.let { bundle ->
            val movie = bundle.getParcelable("movie") as Movie
            if (movie != null) {
                showDetailData(movie)
                progressbar.visibility = View.GONE
                sv_background.visibility = View.VISIBLE
            }
            else {
                progressbar.visibility = View.GONE
                sv_background.visibility = View.GONE
            }
        }
    }

    fun showDetailData(movie: Movie) {
        title = "Detail"
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.posterPath).into(iv_photo)
        tv_year.text = movie.releaseDate
        tv_title.text = movie.title
        tv_score.text = resources.getString(R.string.score)
        tv_rating.text = "${movie.voteAverage}"
        tv_runtime.text = getRuntime(movie.runtime)
        tv_description.text = movie.overview
    }

    private fun getRuntime(runtime: Long): String {
        val hour = (runtime / 60).toString().split(".").get(0)
        val minute = runtime.rem(60)
        return "${hour}h ${minute}m"
    }
}

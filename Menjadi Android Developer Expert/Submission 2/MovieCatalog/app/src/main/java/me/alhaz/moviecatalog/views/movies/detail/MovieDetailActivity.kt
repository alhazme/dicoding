package me.alhaz.moviecatalog.views.movies.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.moviecatalog.R
import me.alhaz.snippet.movieapp.models.Movie

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
            bundle.getParcelable<Movie>("movie")?.let { movie ->
                showDetailData(movie)
            }
        }
    }

    private fun showDetailData(movie: Movie) {
        title = "Detail"
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        movie?.let {
            Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.posterPath).into(iv_photo)
            tv_year.text = movie.releaseDate.split("-").get(0)
            tv_title.text = movie.title
            tv_rating.text = "${movie.voteAverage}"
            tv_runtime.text = getRuntime(movie.runtime)

            var locale = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                locale = resources.configuration.locales.get(0).language
            } else{
                //noinspection deprecation
                locale = resources.configuration.locale.language
            }

            if (locale == "in") {
                tv_description.text = movie.overview_id
            }
            else {
                tv_description.text = movie.overview
            }
        }
    }

    private fun getRuntime(runtime: Long): String {
        val hour = (runtime / 60).toString().split(".").get(0)
        val minute = runtime.rem(60)
        return "${hour}h ${minute}m"
    }

}

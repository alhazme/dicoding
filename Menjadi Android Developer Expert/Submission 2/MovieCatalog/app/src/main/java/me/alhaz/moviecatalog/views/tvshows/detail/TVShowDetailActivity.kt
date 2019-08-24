package me.alhaz.moviecatalog.views.tvshows.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.model.TVShow

class TVShowDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_detail)

        getIntentData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getIntentData() {
        intent.extras?.let {
            it.getParcelable<TVShow>("tvshow")?.let { tvShow ->
                showDetailData(tvShow)
            }
        }
    }

    private fun showDetailData(tvshow: TVShow) {
        title = "Detail"
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        tvshow?.let {tvShow ->
            Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvShow.posterPath).into(iv_photo)
            tv_year.text = tvShow.firstAirDate.split("-").get(0)
            tv_title.text = tvShow.name
            tv_rating.text = tvShow.voteAverage.toString()
            tv_runtime.text = "${tvShow.numberOfEpisodes} Episodes"

            var locale = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                locale = resources.configuration.locales.get(0).language
            } else{
                //noinspection deprecation
                locale = resources.configuration.locale.language
            }

            if (locale == "in") {
                tv_description.text = tvShow.overview_id
            }
            else {
                tv_description.text = tvShow.overview
            }
        }
    }
}

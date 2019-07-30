package me.alhaz.snippet.movieapp.views.tvshows.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tvshow_detail.*
import kotlinx.android.synthetic.main.activity_tvshow_detail.iv_photo
import kotlinx.android.synthetic.main.activity_tvshow_detail.tv_description
import kotlinx.android.synthetic.main.activity_tvshow_detail.tv_rating
import kotlinx.android.synthetic.main.activity_tvshow_detail.tv_runtime
import kotlinx.android.synthetic.main.activity_tvshow_detail.tv_title
import kotlinx.android.synthetic.main.activity_tvshow_detail.tv_year
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow

class TVShowDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: TVShowDetailViewModel

    var tvShowID: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_detail)

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
            tvShowID = it.getLong("tvshow_id", 0)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(TVShowDetailViewModel::class.java)
        EspressoIdlingResource.increment()
        viewModel.getTVShowDetail(tvShowID).observe(this, Observer {
            showDetailData(it)
            ly_loading.setVisibility(View.GONE)
            ly_content.setVisibility(View.VISIBLE)
            if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
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

    private fun showDetailData(tvShow: TVShow) {
        Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvShow.posterPath).into(iv_photo)
        tv_year.text = tvShow.firstAirDate.split("-").get(0)
        tv_title.text = tvShow.name
        tv_rating.text = tvShow.voteAverage.toString()
        tv_runtime.text = "${tvShow.numberOfEpisodes} Episodes"
        tv_description.text = tvShow.overview
    }
}

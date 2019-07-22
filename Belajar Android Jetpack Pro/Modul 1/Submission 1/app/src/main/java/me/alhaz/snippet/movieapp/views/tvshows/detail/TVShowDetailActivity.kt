package me.alhaz.snippet.movieapp.views.tvshows.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_tvshow_detail.*
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.models.TVShow

class TVShowDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: TVShowDetailViewModel

    var tvShowID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_detail)

        setupViewModel()
        getIntentData()
        showDetailData(tvShowID)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(TVShowDetailViewModel::class.java);
    }

    private fun getIntentData() {
        intent.extras?.let {
            tvShowID = it.getInt("tvshow_id", 0)
        }
    }

    fun showDetailData(tvShowID: Int) {
        title = "Detail"
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        val tvShow: TVShow? = viewModel.getTVShowDetail(tvShowID)
        tvShow?.let {
            val imageId = resources.getIdentifier(tvShow.photo, "drawable", packageName)
            iv_photo.setImageResource(imageId)
            tv_year.text = tvShow.year.toString()
            tv_title.text = tvShow.title
            tv_rating.text = tvShow.score.toString()
            tv_runtime.text = tvShow.runtime
            tv_description.text = tvShow.overview
        }
    }
}

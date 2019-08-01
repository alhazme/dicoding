package me.alhaz.snippet.movieapp.views.tvshows.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
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
import me.alhaz.snippet.movieapp.helper.ViewModelFactory
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity

class TVShowDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: TVShowDetailViewModel

    private lateinit var tvShow : TVShowEntity
    var tvShowID: Long = 0
    private var menuItem: Menu? = null

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setMenuIsTVShowFavorite(tvShow)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let { menuItem ->
            if (menuItem.itemId == R.id.menu_favorite) {
                if (tvShow.favorite == 1) {
                    setUnfavorite(tvShowID)
                    return true
                }
                else {
                    setFavorite(tvShowID)
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite(tvShowID: Long) {
        menuItem?.let { menuItem ->
            viewModel.setTVShowFavorite(tvShowID)
            setMenuIsTVShowFavorite(tvShow)
        }
    }

    private fun setUnfavorite(tvShowID: Long) {
        menuItem?.let { menuItem ->
            viewModel.setTVShowUnfavorite(tvShowID)
            setMenuIsTVShowFavorite(tvShow)
        }
    }

    private fun setMenuIsTVShowFavorite(tvShow: TVShowEntity) {
        menuItem?.let { menuItem ->
            if (tvShow.favorite == 1) {
                menuItem.get(0).setIcon(R.drawable.ic_favorite)
            }
            else {
                menuItem.get(0).setIcon(R.drawable.ic_star)
            }
        }
    }

    private fun getIntentData() {
        intent.extras?.let {
            tvShowID = it.getLong("tvshow_id", 0)
        }
    }

    private fun setupLayout() {
        title = "Detail"
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        ly_loading.setVisibility(View.VISIBLE)
        ly_content.setVisibility(View.GONE)
    }

    private fun obtainViewModel(activity: AppCompatActivity): TVShowDetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TVShowDetailViewModel::class.java)
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel(this)
        EspressoIdlingResource.increment()
        viewModel.getTVShowDetail(tvShowID).observe(this, Observer { tvShowEntity ->
            tvShow = tvShowEntity
            setMenuIsTVShowFavorite(tvShowEntity)
            showDetailData(tvShowEntity)

            if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    private fun showDetailData(tvShow: TVShowEntity) {
        ly_loading.setVisibility(View.GONE)
        ly_content.setVisibility(View.VISIBLE)
        Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvShow.posterPath).into(iv_photo)
        tv_year.text = tvShow.firstAirDate.split("-").get(0)
        tv_title.text = tvShow.name
        tv_rating.text = tvShow.voteAverage.toString()
        tv_runtime.text = "${tvShow.numberOfEpisodes} Episodes"
        tv_description.text = tvShow.overview
    }
}

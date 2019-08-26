package me.alhaz.moviecatalog.views.tvshows.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tvshow_detail.*
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.helper.EspressoIdlingResource
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity
import me.alhaz.moviecatalog.viewmodels.TVShowViewModelFactory

class TVShowDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: TVShowDetailViewModel
    private lateinit var tvShow : TVShowEntity
    private var tvShowID: Long = 0
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
        menuItem?.let {
            viewModel.setTVShowFavorite(tvShowID)
            setMenuIsTVShowFavorite(tvShow)
        }
    }

    private fun setUnfavorite(tvShowID: Long) {
        menuItem?.let {
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
        intent.extras?.let { bundle ->
            tvShowID = bundle.getLong("tvshow_id", 0)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): TVShowDetailViewModel {
        val factory = TVShowViewModelFactory.getInstance(activity.application)
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

    private fun setupLayout() {
        title = resources.getString(R.string.detail)
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun showDetailData(tvShow: TVShowEntity) {
        sv_background.visibility = View.VISIBLE
        progressbar.visibility = View.GONE
        Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvShow.posterPath).into(iv_photo)
        tv_year.text = tvShow.firstAirDate.split("-").get(0)
        tv_title.text = tvShow.name
        tv_score.text = resources.getString(R.string.score)
        tv_rating.text = tvShow.voteAverage.toString()
        tv_runtime.text = "${tvShow.numberOfEpisodes} " + resources.getString(R.string.episodes)

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

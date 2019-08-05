package me.alhaz.snippet.movieapp.views.movies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.helper.EspressoIdlingResource
import me.alhaz.snippet.movieapp.helper.ViewModelFactory
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.views.movies.list.MovieListViewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel

    private lateinit var movie : MovieEntity
    var movieID: Long = 0
    private var menuItem: Menu? = null

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        menuItem = menu
        setMenuIsMovieFavorite(movie)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let { menuItem ->
            if (menuItem.itemId == R.id.menu_favorite) {
                if (movie.favorite == 1) {
                    setUnfavorite(movieID)
                    return true
                }
                else {
                    setFavorite(movieID)
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite(movieID: Long) {
        menuItem?.let {
            viewModel.setMovieFavorite(movieID)
            setMenuIsMovieFavorite(movie)
        }
    }

    private fun setUnfavorite(movieID: Long) {
        menuItem?.let {
            viewModel.setMovieUnfavorite(movieID)
            setMenuIsMovieFavorite(movie)
        }
    }

    private fun setMenuIsMovieFavorite(movie: MovieEntity) {
        menuItem?.let { menuItem ->
            if (movie.favorite == 1) {
                menuItem.get(0).setIcon(R.drawable.ic_favorite)
            }
            else {
                menuItem.get(0).setIcon(R.drawable.ic_star)
            }
        }
    }

    private fun getIntentData() {
        intent.extras?.let {
            movieID = it.getLong("movie_id", 0)
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

    private fun obtainViewModel(activity: AppCompatActivity): MovieDetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieDetailViewModel::class.java)
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel(this)
        EspressoIdlingResource.increment()
        viewModel.getMovieDetail(movieID).observe(this, Observer { movieEntity ->
            movie = movieEntity
            setMenuIsMovieFavorite(movieEntity)
            showDetailData(movieEntity)

            if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    private fun showDetailData(movie: MovieEntity) {
        ly_loading.setVisibility(View.GONE)
        ly_content.setVisibility(View.VISIBLE)
        Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.posterPath).into(iv_photo)
        tv_year.text = movie.releaseDate.split("-").get(0)
        tv_title.text = movie.title
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

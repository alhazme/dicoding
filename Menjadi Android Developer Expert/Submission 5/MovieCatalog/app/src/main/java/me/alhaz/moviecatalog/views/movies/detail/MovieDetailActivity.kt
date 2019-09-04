package me.alhaz.moviecatalog.views.movies.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.helper.EspressoIdlingResource
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.moviecatalog.viewmodels.MovieViewModelFactory
import me.alhaz.snippet.movieapp.models.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var movie : Movie
    private var movieEntity : MovieEntity? = null

    private var movieID: Long = 0
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
        setMenuIsMovieFavorite(movieEntity)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let { menuItem ->
            if (menuItem.itemId == R.id.menu_favorite) {
                if (movieEntity != null) {
                    setUnfavorite()
                    return true
                }
                else {
                    setFavorite()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite() {
        menuItem?.let {
            if (movie != null) {
                movieEntity = viewModel.setMovieFavorite(movie)
                setMenuIsMovieFavorite(movieEntity)
            }
        }
    }

    private fun setUnfavorite() {
        menuItem?.let {
            if (movie != null) {
                movieEntity = viewModel.setMovieUnfavorite(movie)
                setMenuIsMovieFavorite(movieEntity)
            }
        }
    }

    private fun setMenuIsMovieFavorite(movieEntity: MovieEntity?) {
        menuItem?.let { menuItem ->
            if (movieEntity != null) {
                movieEntity?.let {
                    if (it.favorite == 1) {
                        menuItem.get(0).setIcon(R.drawable.ic_favorite)
                    }
                    else {
                        menuItem.get(0).setIcon(R.drawable.ic_star)
                    }
                }
            }
            else {
                menuItem.get(0).setIcon(R.drawable.ic_star)
            }
        }
    }

    private fun getIntentData() {
        intent.extras?.let { bundle ->
            movieID = bundle.getLong("movie_id", 0)
        }
    }

    private fun setupLayout() {
        title = resources.getString(R.string.detail)
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MovieDetailViewModel {
        val factory = MovieViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieDetailViewModel::class.java)
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel(this)
        EspressoIdlingResource.increment()
        movieEntity = viewModel.isMovieFavorite(movieID)
        viewModel.getMovieDetail(movieID).observe(this, Observer { movie ->
            this.movie = movie
            showDetailData(movie)
            setMenuIsMovieFavorite(movieEntity)

            if (!EspressoIdlingResource.getEspressoIdlingResourceForMainActivity().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    private fun showDetailData(movie: Movie) {
        sv_background.visibility = View.VISIBLE
        progressbar.visibility = View.GONE
        Glide.with(this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.posterPath).into(iv_photo)
        tv_year.text = movie.releaseDate.split("-").get(0)
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

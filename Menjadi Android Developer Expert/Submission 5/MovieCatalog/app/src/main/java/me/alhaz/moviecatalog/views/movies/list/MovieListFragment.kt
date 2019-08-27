package me.alhaz.moviecatalog.views.movies.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
//import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_movie_list.*

import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.valueobject.Status
import me.alhaz.moviecatalog.viewmodels.MovieViewModelFactory
import me.alhaz.moviecatalog.views.movies.detail.MovieDetailActivity
import me.alhaz.snippet.movieapp.models.Movie

class MovieListFragment : Fragment() {

    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var viewModel: MovieListViewModel

    private lateinit var searchView: SearchView
    private var defaultMovies = ArrayList<Movie>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            fragmentActivity = it
            setupToolbar(it)
            setupViewModel(it)
            setupAdapter(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.let {inflater ->
            menu?.let { menu ->
                inflater.inflate(R.menu.main_menu, menu)
                setupSearchView(menu)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let { item ->
            if (item.getItemId() === me.alhaz.moviecatalog.R.id.action_change_settings) {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupSearchView(menu: Menu) {
        val searchMenu = menu.findItem(R.id.action_search)
        searchView = searchMenu.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    progressbar.visibility = View.VISIBLE
                    rv_movies.visibility = View.GONE
                    if (it.length > 0) {
                        viewModel.searchMovie(it).observe(fragmentActivity, Observer { movies ->
                            movieListAdapter.removeAllMovies()
                            if (movies.size > 0) {
                                progressbar.visibility = View.GONE
                                rv_movies.visibility = View.VISIBLE
                                movieListAdapter.addMovies(movies)
                            }
                            else {
                                progressbar.visibility = View.GONE
                                rv_movies.visibility = View.GONE
                            }
                        })
                    }
                    else {
                        movieListAdapter.removeAllMovies()
                        if (defaultMovies.size > 0) {
                            progressbar.visibility = View.GONE
                            rv_movies.visibility = View.VISIBLE
                            movieListAdapter.addMovies(defaultMovies)
                        }
                        else {
                            progressbar.visibility = View.GONE
                            rv_movies.visibility = View.GONE
                        }
                    }
                }
                val keyboard = fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(View(fragmentActivity).windowToken, 0)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (newText.length == 0) {
                        movieListAdapter.removeAllMovies()
                        if (defaultMovies.size > 0) {
                            progressbar.visibility = View.GONE
                            rv_movies.visibility = View.VISIBLE
                            movieListAdapter.addMovies(defaultMovies)
                        }
                        else {
                            progressbar.visibility = View.GONE
                            rv_movies.visibility = View.GONE
                        }
                    }
                }
                return false
            }

        })
    }

    private fun obtainViewModel(activity: FragmentActivity): MovieListViewModel {
        val factory = MovieViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieListViewModel::class.java)
    }

    private fun setupViewModel(activity: FragmentActivity) {
        viewModel = obtainViewModel(activity)
        viewModel.getListMovie().observe(this, Observer { movies ->
            defaultMovies = movies
            if (movies.size > 0) {
                progressbar.visibility = View.GONE
                rv_movies.visibility = View.VISIBLE
                movieListAdapter.addMovies(movies)
            }
            else {
                progressbar.visibility = View.GONE
                rv_movies.visibility = View.GONE
                movieListAdapter.removeAllMovies()
            }
        })
    }

    private fun setupLayout() {
        rv_movies.setHasFixedSize(true)
        rv_movies.layoutManager = LinearLayoutManager(activity)
    }

    private fun setupToolbar(fragmentActivity: FragmentActivity) {
        (fragmentActivity as AppCompatActivity).setSupportActionBar(toolbar)
        (fragmentActivity as AppCompatActivity).supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun setupAdapter(context: Context) {
        movieListAdapter = MovieListAdapter(context, clickListener = { movie ->
            openDetailMoviePage(movie.id)
        })
        rv_movies.adapter = movieListAdapter
    }

    private fun openDetailMoviePage(movieID: Long) {
        activity?.let {
            val intent = Intent(it, MovieDetailActivity::class.java)
            intent.putExtra("movie_id", movieID)
            it.startActivity(intent)
        }
    }


}

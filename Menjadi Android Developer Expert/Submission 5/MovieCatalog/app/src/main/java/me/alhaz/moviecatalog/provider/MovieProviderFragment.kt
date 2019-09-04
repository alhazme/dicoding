package me.alhaz.moviecatalog.provider

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_provider.*
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.provider.MovieContentProvider.Companion.CONTENT_URI
import me.alhaz.moviecatalog.views.movies.detail.MovieDetailActivity
import me.alhaz.snippet.movieapp.models.Movie

class MovieProviderFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var movieProviderAdapter: MovieProviderAdapter
    private lateinit var fragmentActivity: FragmentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_provider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { fragmentActivity ->
            this.fragmentActivity = fragmentActivity
            initLoader()
        }
    }

    override fun onResume() {
        super.onResume()
        restartLoader()
    }

    private fun setupLayout() {
        rv_provider_movies.setHasFixedSize(true)
        rv_provider_movies.layoutManager = LinearLayoutManager(activity)
    }

    private fun initLoader() {
        loaderManager.initLoader(MovieContentProvider.REQUEST_CODE_LIST, null, this)
    }

    private fun showData(cursor: Cursor) {
        if (cursor.count > 0) {
            progressbar.visibility = View.GONE
            rv_provider_movies.visibility = View.VISIBLE
        }
        else {
            progressbar.visibility = View.GONE
            rv_provider_movies.visibility = View.GONE
        }
    }

    private fun openDetailMoviePage(movieID: Long) {
        activity?.let {
            val intent = Intent(it, MovieDetailActivity::class.java)
            intent.putExtra("movie_id", movieID)
            it.startActivity(intent)
        }
    }

    fun restartLoader() {
        loaderManager.restartLoader(MovieContentProvider.REQUEST_CODE_LIST, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(fragmentActivity, MovieContentProvider.CONTENT_URI, null, null, null, null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        data?.let { cursor ->
            movieProviderAdapter = MovieProviderAdapter(fragmentActivity.applicationContext, cursor, clickListener = { cursor ->
                val movieID = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                openDetailMoviePage(movieID)
            })
            rv_provider_movies.adapter = movieProviderAdapter
            movieProviderAdapter.notifyDataSetChanged()
            showData(cursor)
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        movieProviderAdapter.cursor = null
    }


}

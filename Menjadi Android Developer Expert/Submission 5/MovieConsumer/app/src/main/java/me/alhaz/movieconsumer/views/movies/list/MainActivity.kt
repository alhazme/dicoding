package me.alhaz.movieconsumer.views.movies.list

import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.alhaz.movieconsumer.R
import me.alhaz.movieconsumer.data.DataConnector
import android.os.HandlerThread
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import me.alhaz.movieconsumer.data.DataObserver
import me.alhaz.movieconsumer.data.GetData
import me.alhaz.movieconsumer.models.Movie
import me.alhaz.movieconsumer.views.movies.detail.MovieDetailActivity

interface LoadNotesCallback {
    fun postExecute(movies: Cursor)
}

class MainActivity : AppCompatActivity(), LoadNotesCallback {

    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var dataObserver: DataObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupLayout()
        setupContentResolver()
    }

    override fun onResume() {
        super.onResume()
        GetData(this, this).execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        getContentResolver().unregisterContentObserver(dataObserver)
    }

    fun setupContentResolver() {

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        dataObserver = DataObserver(handler, this)

        val contentResolver = getContentResolver()
        contentResolver.registerContentObserver(DataConnector.getContentURI(), true, dataObserver)
    }

    fun setupLayout() {
        rv_movies.setHasFixedSize(true)
        rv_movies.layoutManager = LinearLayoutManager(this)
        movieListAdapter = MovieListAdapter(this, null, clickListener = { movie ->
            openDetailMoviePage(movie)
        })
        rv_movies.adapter = movieListAdapter
    }

    fun openDetailMoviePage(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }


    override fun postExecute(movies: Cursor) {
        movieListAdapter.cursor = movies
        movieListAdapter.notifyDataSetChanged()
        if (movies.count > 0) {
            progressbar.visibility = View.GONE
            rv_movies.visibility = View.VISIBLE
        }
        else {
            progressbar.visibility = View.VISIBLE
            rv_movies.visibility = View.GONE
        }
    }

}

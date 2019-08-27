package me.alhaz.moviecatalog.views.movies.list

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity
import me.alhaz.snippet.movieapp.models.Movie

class MovieListAdapter(val context: Context, val clickListener: (Movie) -> Unit) : RecyclerView.Adapter<MovieListViewHolder>() {

    var listMovies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(LayoutInflater.from(context).inflate(R.layout.row_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = listMovies.get(position)
        holder.bindItem(context, holder.itemView, movie, clickListener)
    }

    fun addMovie(movie: Movie) {
        listMovies.add(movie)
        notifyDataSetChanged()
    }

    fun addMovies(movies: ArrayList<Movie>) {
        listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    fun removeAllMovies() {
        listMovies.removeAll(listMovies)
        notifyDataSetChanged()
    }

}

class MovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvYear: TextView = view.findViewById(R.id.tv_year)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvRating: TextView = view.findViewById(R.id.tv_rating)
    private val tvRuntime: TextView = view.findViewById(R.id.tv_runtime)
    private val tvDescription: TextView = view.findViewById(R.id.tv_description)
    private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)

    fun bindItem(context: Context, view: View, movie: Movie, clickListener: (Movie) -> Unit) {
        tvYear.text = movie.releaseDate.split("-").get(0)
        tvTitle.text = movie.title
        tvRating.text = "${movie.voteAverage}"
        tvRuntime.text = getRuntime(movie.runtime)
        tvDescription.text = movie.overview
        Glide.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.posterPath).into(ivPhoto)
        view.setOnClickListener {
            clickListener(movie)
        }
    }

    private fun getRuntime(runtime: Long): String {
        val hour = (runtime / 60).toString().split(".").get(0)
        val minute = runtime.rem(60)
        return "${hour}h ${minute}m"
    }


}
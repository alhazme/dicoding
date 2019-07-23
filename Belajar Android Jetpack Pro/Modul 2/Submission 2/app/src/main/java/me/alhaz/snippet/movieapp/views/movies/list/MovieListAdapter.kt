package me.alhaz.snippet.movieapp.views.movies.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie

class MovieListAdapter(val context: Context, private val listMovies: ArrayList<Movie>, val clickListener: (Movie) -> Unit) : RecyclerView.Adapter<MovieListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = listMovies.get(position)
        holder.bindItem(context, holder.itemView, movie, clickListener)
    }

}

class MovieListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvYear: TextView = view.findViewById(R.id.tv_year)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvRating: TextView = view.findViewById(R.id.tv_rating)
    private val tvDescription: TextView = view.findViewById(R.id.tv_description)
    private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)

    fun bindItem(context: Context, view: View, movie: Movie, clickListener: (Movie) -> Unit) {
        tvYear.text = movie.releaseDate.split("-").get(0)
        tvTitle.text = movie.title
        tvRating.text = movie.voteAverage.toString()
        tvDescription.text = movie.overview
        Glide.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.posterPath).into(ivPhoto)
        view.setOnClickListener {
            clickListener(movie)
        }
    }


}
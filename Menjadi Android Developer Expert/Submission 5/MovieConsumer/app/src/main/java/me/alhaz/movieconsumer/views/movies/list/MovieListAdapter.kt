package me.alhaz.movieconsumer.views.movies.list

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.alhaz.movieconsumer.R
import me.alhaz.movieconsumer.models.Movie

class MovieListAdapter(val context: Context, var cursor: Cursor?, val clickListener: (Movie) -> Unit): RecyclerView.Adapter<MovieProviderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieProviderViewHolder {
        return MovieProviderViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item, parent, false))
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        cursor?.let {
            return it.count
        }
        return 0
    }

    override fun onBindViewHolder(holder: MovieProviderViewHolder, position: Int) {
        cursor?.let {
            if (it.moveToPosition(position)) {
                holder.bindItem(context, holder.itemView, it, clickListener)
            }
        }
    }



}

class MovieProviderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvYear: TextView = view.findViewById(R.id.tv_year)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvRating: TextView = view.findViewById(R.id.tv_rating)
    private val tvDescription: TextView = view.findViewById(R.id.tv_description)
    private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)

    fun bindItem(context: Context, view: View, cursor: Cursor, clickListener: (Movie) -> Unit) {

        val movieID = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
        val releaseDate = cursor.getString(cursor.getColumnIndexOrThrow("release_date")).split("-").get(0)
        val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
        val voteAverage = cursor.getDouble(cursor.getColumnIndexOrThrow("vote_average"))
        val overview = cursor.getString(cursor.getColumnIndexOrThrow("overview"))
        val posterPath = cursor.getString(cursor.getColumnIndexOrThrow("poster_path"))
        val runtime = cursor.getLong(cursor.getColumnIndexOrThrow("runtime"))

        val movie = Movie()
        movie.id = movieID
        movie.title = title
        movie.posterPath = posterPath
        movie.releaseDate = releaseDate
        movie.voteAverage = voteAverage
        movie.runtime = runtime
        movie.overview = overview

        tvYear.text = releaseDate
        tvTitle.text = title
        tvRating.text = voteAverage.toString()
        tvDescription.text = overview

        Glide.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + posterPath).into(ivPhoto)

        view.setOnClickListener {
            clickListener(movie)
        }
    }


}
package me.alhaz.moviecatalog.provider

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.alhaz.moviecatalog.R

 class MovieProviderAdapter(var context: Context, var cursor: Cursor?, val clickListener: (Cursor) -> Unit): RecyclerView.Adapter<MovieProviderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieProviderViewHolder {
        return MovieProviderViewHolder(LayoutInflater.from(context).inflate(R.layout.row_movie, parent, false))
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

    fun bindItem(context: Context, view: View, cursor: Cursor, clickListener: (Cursor) -> Unit) {

        val releaseDate = cursor.getString(cursor.getColumnIndexOrThrow("release_date")).split("-").get(0)
        val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
        val voteAverage = cursor.getString(cursor.getColumnIndexOrThrow("vote_average"))
        val overview = cursor.getString(cursor.getColumnIndexOrThrow("overview"))
        val posterPath = cursor.getString(cursor.getColumnIndexOrThrow("poster_path"))

        tvYear.text = releaseDate
        tvTitle.text = title
        tvRating.text = voteAverage
        tvDescription.text = overview

        context?.let { ctx ->
            Glide.with(ctx).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + posterPath).into(ivPhoto)
        }

        view.setOnClickListener {
            clickListener(cursor)
        }
    }


}
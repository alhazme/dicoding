package me.alhaz.moviecatalog.views.movies.favorite

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity

class MovieFavoriteAdapter(val context: Context, val clickListener: (MovieEntity) -> Unit) : PagedListAdapter<MovieEntity, MovieFavoriteViewHolder>(diffCallback) { // For Pagination

    // For Pagination

    companion object {
        private val diffCallback = object: DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        return MovieFavoriteViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bindItem(context, holder.itemView, movie, clickListener)
    }

}

class MovieFavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvYear: TextView = view.findViewById(R.id.tv_year)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvRating: TextView = view.findViewById(R.id.tv_rating)
    private val tvDescription: TextView = view.findViewById(R.id.tv_description)
    private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)

    fun bindItem(context: Context, view: View, movieEntity: MovieEntity?, clickListener: (MovieEntity) -> Unit) {
        movieEntity?.let { movie ->
            tvYear.text = movie.releaseDate.split("-").get(0)
            tvTitle.text = movie.title
            tvRating.text = movie.voteAverage.toString()

            var locale = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                locale = context.resources.configuration.locales.get(0).language
            } else {
                //noinspection deprecation
                locale = context.resources.configuration.locale.language
            }

            if (locale == "in") {
                tvDescription.text = movie.overview_id
            } else {
                tvDescription.text = movie.overview
            }

            Glide.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movie.posterPath).into(ivPhoto)
            view.setOnClickListener {
                clickListener(movie)
            }
        }
    }


}
package me.alhaz.snippet.movieapp.views.tvshows.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShowEntity

class TVShowListAdapter(val context: Context, val clickListener: (TVShowEntity) -> Unit) : PagedListAdapter<TVShowEntity, TVShowListViewHolder>(diffCallback) { // For Pagination

    // For Pagination

    companion object {
        private val diffCallback = object: DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowListViewHolder {
        return TVShowListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_tvshow,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TVShowListViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bindItem(context, holder.itemView, tvShow, clickListener)
    }

}

class TVShowListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvYear: TextView = view.findViewById(R.id.tv_year)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvRating: TextView = view.findViewById(R.id.tv_rating)
    private val tvDescription: TextView = view.findViewById(R.id.tv_description)
    private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)

    fun bindItem(context: Context, view: View, tvShowEntity: TVShowEntity?, clickListener: (TVShowEntity) -> Unit) {
        tvShowEntity?.let { tvShowEntity ->
            tvYear.text = tvShowEntity.firstAirDate.split("-").get(0)
            tvTitle.text = tvShowEntity.name
            tvRating.text = tvShowEntity.voteAverage.toString()
            tvDescription.text = tvShowEntity.overview
            Glide.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvShowEntity.posterPath).into(ivPhoto)
            view.setOnClickListener {
                clickListener(tvShowEntity)
            }
        }
    }


}
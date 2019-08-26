package me.alhaz.moviecatalog.views.tvshows.list

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
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShow
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity

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
    private val tvRuntime: TextView = view.findViewById(R.id.tv_runtime)
    private val tvDescription: TextView = view.findViewById(R.id.tv_description)
    private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)

    fun bindItem(context: Context, view: View, tvShowEntity: TVShowEntity?, clickListener: (TVShowEntity) -> Unit) {
        tvShowEntity?.let { tvShow ->
            Glide.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvShow.posterPath)
                .into(ivPhoto)
            tvYear.text = tvShow.firstAirDate.split("-").get(0)
            tvTitle.text = tvShow.name
            tvRating.text = tvShow.voteAverage.toString()
            tvRuntime.text = "${tvShow.numberOfEpisodes} Episodes"

            var locale = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                locale = context.resources.configuration.locales.get(0).language
            } else {
                //noinspection deprecation
                locale = context.resources.configuration.locale.language
            }

            if (locale == "in") {
                tvDescription.text = tvShow.overview_id
            } else {
                tvDescription.text = tvShow.overview
            }

            view.setOnClickListener {
                clickListener(tvShow)
            }
        }
    }


}
package me.alhaz.moviecatalog.views.tvshows.list

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import me.alhaz.moviecatalog.R
import me.alhaz.moviecatalog.model.TVShow

class TVShowListAdapter(val context: Context, private val listTVShows: ArrayList<TVShow>, val clickListener: (TVShow) -> Unit) : RecyclerView.Adapter<TVShowListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowListViewHolder {
        return TVShowListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.row_tvshow,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listTVShows.size
    }

    override fun onBindViewHolder(holder: TVShowListViewHolder, position: Int) {
        val listTVShow = listTVShows.get(position)
        holder.bindItem(context, holder.itemView, listTVShow, clickListener)
    }

}

class TVShowListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvYear: TextView = view.findViewById(R.id.tv_year)
    private val tvTitle: TextView = view.findViewById(R.id.tv_title)
    private val tvRating: TextView = view.findViewById(R.id.tv_rating)
    private val tvRuntime: TextView = view.findViewById(R.id.tv_runtime)
    private val tvDescription: TextView = view.findViewById(R.id.tv_description)
    private val ivPhoto: ImageView = view.findViewById(R.id.iv_photo)

    fun bindItem(context: Context, view: View, tvShow: TVShow, clickListener: (TVShow) -> Unit) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + tvShow.posterPath).into(ivPhoto)
        tvYear.text = tvShow.firstAirDate.split("-").get(0)
        tvTitle.text = tvShow.name
        tvRating.text = tvShow.voteAverage.toString()
        tvRuntime.text = "${tvShow.numberOfEpisodes} Episodes"

        var locale = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            locale = context.resources.configuration.locales.get(0).language
        }
        else {
            //noinspection deprecation
            locale = context.resources.configuration.locale.language
        }

        if (locale == "in") {
            tvDescription.text = tvShow.overview_id
        }
        else {
            tvDescription.text = tvShow.overview
        }

        view.setOnClickListener {
            clickListener(tvShow)
        }
    }


}
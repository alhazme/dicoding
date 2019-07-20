package me.alhaz.snippet.movieapp.views.tvshows.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.models.TVShow

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
        tvYear.text = tvShow.year.toString()
        tvTitle.text = tvShow.title
        tvRating.text = tvShow.score.toString()
        tvRuntime.text = tvShow.runtime
        tvDescription.text = tvShow.overview
        val imageId = context.resources.getIdentifier(tvShow.photo, "drawable", context.packageName)
        ivPhoto.setImageResource(imageId)
        view.setOnClickListener {
            clickListener(tvShow)
        }
    }


}
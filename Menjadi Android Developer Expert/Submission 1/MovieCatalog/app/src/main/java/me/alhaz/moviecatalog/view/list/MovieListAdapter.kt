package me.alhaz.moviecatalog.view.list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.row_item.view.*
import me.alhaz.moviecatalog.R
import me.alhaz.snippet.movieapp.models.Movie

class MovieListAdapter(val context: Context, private val listMovies: ArrayList<Movie>, val clickListener: (Movie) -> Unit): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.row_item, null)

        val tvYear: TextView = view.tv_year
        val tvTitle: TextView = view.tv_title
        val tvRating: TextView = view.tv_rating
        val tvRuntime: TextView = view.tv_runtime
        val tvDescription: TextView = view.tv_description
        val ivPhoto: ImageView = view.iv_photo

        val movie = listMovies.get(position)
        tvYear.text = movie.year.toString()
        tvTitle.text = movie.title
        tvRating.text = movie.score.toString()
        tvRuntime.text = movie.runtime
        tvDescription.text = movie.overview
        val imageId = context.resources.getIdentifier(movie.photo, "drawable", context.packageName)
        ivPhoto.setImageResource(imageId)
        view.setOnClickListener {
            clickListener(movie)
        }
        return view

    }

    override fun getItem(position: Int): Movie {
        return listMovies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listMovies.size
    }


}
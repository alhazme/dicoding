package me.alhaz.submission3.view.fragment.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.alhaz.submission3.R
import me.alhaz.submission3.model.Database.Favorite
import me.alhaz.submission3.model.Parser.Event


class FavoriteAdapter (val context: Context, private val favorites: List<Favorite>, val clickListener: (Favorite) -> Unit) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        return EventViewHolder(LayoutInflater.from(context).inflate(R.layout.next_item, parent, false))
    }

    override fun getItemCount(): Int { //To change body of created functions use File | Settings | File Templates.
        return favorites.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(holder.itemView, favorites[position], clickListener)
    }

}

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val dateTextView: TextView = view.findViewById<TextView>(R.id.tv_date)
    private val homeTeamTextView: TextView = view.findViewById<TextView>(R.id.tv_home)
    private val homeTeamScoreTextView: TextView = view.findViewById<TextView>(R.id.tv_home_score)
    private val awayTeamTextView: TextView = view.findViewById<TextView>(R.id.tv_away)
    private val awayTeamScoreTextView: TextView = view.findViewById<TextView>(R.id.tv_away_score)

    fun bindItem(view: View, favorite: Favorite, clickListener: (Favorite) -> Unit) {

        dateTextView.text = favorite.date
        homeTeamTextView.text = favorite.teamHomeName
        if (favorite.teamHomeScore == null) {
            homeTeamScoreTextView.text = "-"
        }
        else {
            homeTeamScoreTextView.text = favorite.teamHomeScore.toString()
        }

        awayTeamTextView.text = favorite.teamAwayName
        if (favorite.teamAwayScore == null) {
            awayTeamScoreTextView.text = "-"
        }
        else {
            awayTeamScoreTextView.text = favorite.teamAwayScore.toString()
        }

        view.setOnClickListener { clickListener(favorite) }
    }
}
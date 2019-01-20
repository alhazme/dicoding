package me.alhaz.submission3.view.fragment.next

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.alhaz.submission3.R
import me.alhaz.submission3.model.Parser.Event


class NextAdapter (val context: Context, private val events: List<Event>, val clickListener: (Event) -> Unit) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        return EventViewHolder(LayoutInflater.from(context).inflate(R.layout.next_item, parent, false))
    }

    override fun getItemCount(): Int { //To change body of created functions use File | Settings | File Templates.
        return events.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(holder.itemView, events[position], clickListener)
    }

}

class EventViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val dateTextView: TextView = view.findViewById<TextView>(R.id.tv_date)
    private val homeTeamTextView: TextView = view.findViewById<TextView>(R.id.tv_home)
    private val homeTeamScoreTextView: TextView = view.findViewById<TextView>(R.id.tv_home_score)
    private val awayTeamTextView: TextView = view.findViewById<TextView>(R.id.tv_away)
    private val awayTeamScoreTextView: TextView = view.findViewById<TextView>(R.id.tv_away_score)

    fun bindItem(view: View, event: Event, clickListener: (Event) -> Unit) {

        dateTextView.text = event.dateEvent
        homeTeamTextView.text = event.strHomeTeam
        if (event.intHomeScore == null) {
            homeTeamScoreTextView.text = "-"
        }
        else {

            homeTeamScoreTextView.text = event.intHomeScore.toString()
        }

        awayTeamTextView.text = event.strAwayTeam
        if (event.intAwayScore == null) {
            awayTeamScoreTextView.text = "-"
        }
        else {
            awayTeamScoreTextView.text = event.intAwayScore.toString()
        }

        view.setOnClickListener { clickListener(event) }
    }
}
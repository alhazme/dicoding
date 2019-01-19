package me.alhaz.kotlinankosqlite.View.Fragment.Teams

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import me.alhaz.kotlinankosqlite.Model.Parser.Team
import me.alhaz.kotlinankosqlite.R
import org.jetbrains.anko.*
import me.alhaz.kotlinankosqlite.R.id.team_badge
import me.alhaz.kotlinankosqlite.R.id.team_name

class TeamsAdapter (private val teams: List<Team>, val clickListener: (Team) -> Unit) : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int { //To change body of created functions use File | Settings | File Templates.
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], clickListener)
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(team: Team, clickListener: (Team) -> Unit) {
        Picasso.get().load(team.teamBadge).into(teamBadge)
        teamName.text = team.teamName
        itemView.setOnClickListener { clickListener(team) }
    }
}

class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}

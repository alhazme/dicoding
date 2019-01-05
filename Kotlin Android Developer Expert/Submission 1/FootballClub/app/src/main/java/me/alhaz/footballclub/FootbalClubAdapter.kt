package me.alhaz.footballclub

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity

class FootbalClubAdapter(var list: ArrayList<FootballClub> = arrayListOf()) : RecyclerView.Adapter<FootbalClubAdapter.FootballViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballViewHolder {
        return FootballViewHolder(FootballClubItemUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        val footballClub = list[position]
        holder.nameTextView.text = footballClub.name
        Glide.with(holder.itemView.context).load(footballClub.image).into(holder.logoImageView)
        holder.itemView.setOnClickListener {

            /**
             *
             * Open detail page and show data from Recycler View List
             * Using Intent Builder Anko Commons
             *
             */

            it.context.startActivity<DetailActivity>("name" to footballClub.name, "image" to footballClub.image, "description" to footballClub.description)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class FootballViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView
        var logoImageView: ImageView

        init {
            nameTextView = itemView.findViewById(FootballClubItemUI.nameTextView)
            logoImageView = itemView.findViewById(FootballClubItemUI.logoImageView)
        }

    }

}
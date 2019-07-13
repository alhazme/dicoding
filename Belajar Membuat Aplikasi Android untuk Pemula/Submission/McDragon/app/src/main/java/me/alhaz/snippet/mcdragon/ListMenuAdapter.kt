package me.alhaz.snippet.mcdragon

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ListMenuAdapter (private val listMenu: ArrayList<Menu>): RecyclerView.Adapter<ListMenuAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Menu)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.rw_menu, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, name, description, price) = listMenu[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvPrice.text = "Rp$price"
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMenu[holder.adapterPosition])
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.iv_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_price)
    }


}
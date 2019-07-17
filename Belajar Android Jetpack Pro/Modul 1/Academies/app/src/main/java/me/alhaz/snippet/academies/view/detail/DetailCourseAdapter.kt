package me.alhaz.snippet.academies.view.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.alhaz.snippet.academies.R
import me.alhaz.snippet.academies.data.ModuleEntity

class DetailCourseAdapter(val context: Context, private val listModules: List<ModuleEntity>) : RecyclerView.Adapter<DetailCourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailCourseViewHolder {
        return DetailCourseViewHolder(LayoutInflater.from(context).inflate(R.layout.items_module_list, parent, false))
    }

    override fun getItemCount(): Int {
        return listModules.size
    }

    override fun onBindViewHolder(holder: DetailCourseViewHolder, position: Int) {
        val module = listModules.get(position)
        holder.bindItem(holder.itemView, module)
    }

}

class DetailCourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textTitle: TextView = view.findViewById(R.id.text_module_title)

    fun bindItem(view: View, module: ModuleEntity) {
        textTitle.text = module.mTitle
    }


}
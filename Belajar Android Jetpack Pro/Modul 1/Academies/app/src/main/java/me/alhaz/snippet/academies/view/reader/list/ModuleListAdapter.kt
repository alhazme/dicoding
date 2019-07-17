package me.alhaz.snippet.academies.view.reader.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.alhaz.snippet.academies.R
import me.alhaz.snippet.academies.data.CourseEntity
import me.alhaz.snippet.academies.data.ModuleEntity

class ModuleListAdapter(val context: Context, private val listModules: List<ModuleEntity>, val clickListener: (Int, ModuleEntity) -> Unit) : RecyclerView.Adapter<ModuleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        return ModuleViewHolder(LayoutInflater.from(context).inflate(R.layout.items_module_list_custom, parent, false))
    }

    override fun getItemCount(): Int {
        return listModules.size
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = listModules.get(position)
        holder.bindItem(holder.itemView, position, module, clickListener)
    }

}

class ModuleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textTitle: TextView = view.findViewById(R.id.text_module_title)
    private val textLastSeen: TextView = view.findViewById(R.id.text_last_seen)

    fun bindItem(view: View, position: Int, module: ModuleEntity, clickListener: (Int, ModuleEntity) -> Unit) {
        textTitle.text = module.mTitle
        view.setOnClickListener {
            clickListener(position, module)
        }
    }


}
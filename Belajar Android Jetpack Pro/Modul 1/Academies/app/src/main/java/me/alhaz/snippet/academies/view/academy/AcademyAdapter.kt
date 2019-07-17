package me.alhaz.snippet.academies.view.academy

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.alhaz.snippet.academies.R
import me.alhaz.snippet.academies.data.CourseEntity

class AcademyAdapter(val context: Context, private val listCourses: List<CourseEntity>, val clickListener: (CourseEntity) -> Unit) : RecyclerView.Adapter<AcademyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademyViewHolder {
        return AcademyViewHolder(LayoutInflater.from(context).inflate(R.layout.items_academy, parent, false))
    }

    override fun getItemCount(): Int {
        return listCourses.size
    }

    override fun onBindViewHolder(holder: AcademyViewHolder, position: Int) {
        val course = listCourses.get(position)
        holder.bindItem(holder.itemView, course, clickListener)
    }

}

class AcademyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTitle: TextView = view.findViewById(R.id.tv_item_title)
    private val tvDescription: TextView = view.findViewById(R.id.tv_item_description)
    private val tvDate: TextView = view.findViewById(R.id.tv_item_date)
    private val imgPoster: ImageView = view.findViewById(R.id.img_poster)

    fun bindItem(view: View, course: CourseEntity, clickListener: (CourseEntity) -> Unit) {
        tvTitle.text = course.title
        tvDescription.text = course.description
        tvDate.text = String.format("Deadline %s", course.deadline)
        Glide.with(imgPoster)
            .load(course.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(imgPoster)
        view.setOnClickListener {
            clickListener(course)
        }
    }


}

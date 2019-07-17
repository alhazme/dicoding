package me.alhaz.snippet.academies.view.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import me.alhaz.snippet.academies.R
import me.alhaz.snippet.academies.utils.DataDummy
import me.alhaz.snippet.academies.view.detail.DetailCourseActivity
import android.content.Intent
import me.alhaz.snippet.academies.data.CourseEntity

class AcademyFragment : Fragment() {

    private lateinit var rvCourse: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var academyAdapter: AcademyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCourse = view.findViewById(R.id.rv_academy)
        progressBar = view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            academyAdapter = AcademyAdapter(it, DataDummy.generateDummyCourses(), clickListener = {
                val courseEntity = it
                openDetailCoursePage(courseEntity)
            })
            rvCourse.layoutManager = LinearLayoutManager(it)
            rvCourse.setHasFixedSize(true)
            rvCourse.adapter = academyAdapter
        }
    }

    private fun openDetailCoursePage(courseEntity: CourseEntity) {
        activity?.let {
            val activity = it
            val intent = Intent(activity, DetailCourseActivity::class.java)
            intent.putExtra("extra_course", courseEntity)
//            intent.putExtra(DetailCourseActivity.EXTRA_COURSE, courseEntity.courseId)
            activity.startActivity(intent)
        }
    }


}

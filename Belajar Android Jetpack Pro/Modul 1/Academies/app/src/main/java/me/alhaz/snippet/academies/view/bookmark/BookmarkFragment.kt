package me.alhaz.snippet.academies.view.bookmark

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
import androidx.core.app.ShareCompat
import android.content.Intent
import me.alhaz.snippet.academies.data.CourseEntity
import me.alhaz.snippet.academies.view.detail.DetailCourseActivity


/**
 * A simple [Fragment] subclass.
 *
 */

class BookmarkFragment : Fragment() {

    private lateinit var rvBookmark: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var bookmarkAdapter: BookmarkAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvBookmark = view.findViewById(R.id.rv_bookmark)
        progressBar = view.findViewById(R.id.progress_bar)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            bookmarkAdapter = BookmarkAdapter(it, DataDummy.generateDummyCourses(), clickListener = {
                val courseEntity = it
                openDetailCoursePage(courseEntity)
            }, shareListener = {
                shareCourse(it)
            })
            rvBookmark.layoutManager = LinearLayoutManager(it)
            rvBookmark.setHasFixedSize(true)
            rvBookmark.adapter = bookmarkAdapter
        }
    }

    fun shareCourse(course: CourseEntity) {
        val mimeType = "text/plain"
        activity?.let {
            ShareCompat.IntentBuilder
                .from(it)
                .setChooserTitle("Bagikan aplikasi ini sekarang.")
                .setText(String.format("Segera daftar kelas %s di dicoding.com", course.title))
                .startChooser()
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

package me.alhaz.snippet.academies.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import me.alhaz.snippet.academies.R
import me.alhaz.snippet.academies.data.CourseEntity
import me.alhaz.snippet.academies.utils.DataDummy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import me.alhaz.snippet.academies.view.reader.CourseReaderActivity


class DetailCourseActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var btnStart: Button
    private lateinit var textTitle: TextView
    private lateinit var textDesc: TextView
    private lateinit var textDate: TextView
    private lateinit var rvModule: RecyclerView
    private lateinit var imagePoster: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: DetailCourseAdapter

    private var courseID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)

        setupLayout()
        getIntentData()
    }

    private fun setupLayout() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }

        progressBar = findViewById(R.id.progress_bar);
        btnStart = findViewById(R.id.btn_start);
        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        textDate = findViewById(R.id.text_date);
        imagePoster = findViewById(R.id.image_poster);

        rvModule = findViewById(R.id.rv_module);
        rvModule.isNestedScrollingEnabled = false
        rvModule.layoutManager = LinearLayoutManager(this)
        rvModule.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(rvModule.context, DividerItemDecoration.VERTICAL)
        rvModule.addItemDecoration(dividerItemDecoration)


    }

    private fun getIntentData() {
        intent.extras?.let {
            it.getParcelable<CourseEntity>("extra_course")?.let {
                val course: CourseEntity = it
                courseID = course.courseId
                val modules = DataDummy.generateDummyModules(course.courseId)
                adapter = DetailCourseAdapter(this, modules)
                rvModule.adapter = adapter
                populateCourse(courseID)
            }
        }

    }

    private fun populateCourse(courseID: String) {
        DataDummy.getCourse(courseID)?.let {
            val courseEntity = it
            textTitle.text = courseEntity.title
            textDesc.text = courseEntity.description
            textDate.text = String.format("Deadline %s", courseEntity.deadline)
            Glide.with(applicationContext)
                .load(courseEntity.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(imagePoster)
            btnStart.setOnClickListener {
                openCourseReaderPage(courseEntity)
            }
        }
    }

    private fun openCourseReaderPage(courseEntity: CourseEntity) {
        val intent = Intent(this, CourseReaderActivity::class.java)
        intent.putExtra("extra_course_id", courseEntity.courseId)
        startActivity(intent)
    }

}

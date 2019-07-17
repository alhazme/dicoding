package me.alhaz.snippet.academies.view.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import me.alhaz.snippet.academies.R
import me.alhaz.snippet.academies.utils.DataDummy
import me.alhaz.snippet.academies.view.reader.list.ModuleListFragment



class CourseReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        getIntentData()
    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag("ModuleListFragment")
        if (fragment == null) {
            fragment = ModuleListFragment()
            fragmentTransaction.add(R.id.frame_container, fragment, "ModuleListFragment")
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    private fun getIntentData() {
        intent.extras?.let {
            it.getString("extra_course_id")?.let {
                val courseID = it
                val module = DataDummy.generateDummyModules(courseID)
                Log.d("1234567890", courseID)
                populateFragment()
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        }
        else {
            super.onBackPressed()
        }

    }
}

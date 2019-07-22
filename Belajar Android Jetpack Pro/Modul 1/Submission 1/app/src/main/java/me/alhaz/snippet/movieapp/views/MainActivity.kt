package me.alhaz.snippet.movieapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import me.alhaz.snippet.movieapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLayout()
    }

    private fun setupLayout() {

        toolbar?.let {
            setSupportActionBar(it)
            supportActionBar?.let {
                it.setDisplayHomeAsUpEnabled(false)
            }
        }

        vp_container.adapter = MainViewPagerAdapter(supportFragmentManager)
        tl_container.setupWithViewPager(vp_container)
    }
}

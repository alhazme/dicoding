package me.alhaz.moviecatalog.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import me.alhaz.moviecatalog.R
import android.content.Intent
import android.provider.Settings


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let { item ->
            if (item.getItemId() === me.alhaz.moviecatalog.R.id.action_change_settings) {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupLayout() {

        setTitle(resources.getString(R.string.app_name))

        toolbar?.let {
            setSupportActionBar(it)
            supportActionBar?.let {
                it.setDisplayHomeAsUpEnabled(false)
            }
        }

        vp_container.adapter = MainViewPagerAdapter(supportFragmentManager, this)
        tl_container.setupWithViewPager(vp_container)
    }
}

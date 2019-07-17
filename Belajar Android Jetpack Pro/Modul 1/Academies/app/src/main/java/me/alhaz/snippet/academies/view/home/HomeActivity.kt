package me.alhaz.snippet.academies.view.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import me.alhaz.snippet.academies.R
import me.alhaz.snippet.academies.view.academy.AcademyFragment
import me.alhaz.snippet.academies.view.bookmark.BookmarkFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupLayout()

        if (savedInstanceState != null) {
            savedInstanceState.getInt("SELECTED_MENU")
        } else {
            navView.selectedItemId = R.id.action_home
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            it.putInt("SELECTED_MENU", navView.selectedItemId)
        }
    }

    private fun setupLayout() {
        navView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.action_home -> {
                fragment = AcademyFragment()
            }
            R.id.action_bookmark -> {
                fragment = BookmarkFragment()
            }
        }
        fragment?.let {
            supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, it)
                .commit()
        }
        true
    }
}

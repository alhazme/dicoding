package me.alhaz.snippet.movieapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import me.alhaz.snippet.movieapp.R
import me.alhaz.snippet.movieapp.views.favorite.FavoriteFragment
import me.alhaz.snippet.movieapp.views.home.HomeFragment
import me.alhaz.snippet.movieapp.views.movies.list.MovieListFragment
import me.alhaz.snippet.movieapp.views.tvshows.list.TVShowListFragment

class MainActivity : AppCompatActivity() {

    private var homeFragment: HomeFragment? = null
    private var favoriteFragment: FavoriteFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragments()
        bn_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setupFragments() {
        homeFragment = HomeFragment()
        favoriteFragment = FavoriteFragment()

        homeFragment?.let {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fl_container, it)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.home_menu -> {
                homeFragment?.let {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fl_container, it)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            R.id.favorite_menu -> {
                favoriteFragment?.let {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fl_container, it)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
        }
        false
    }
}

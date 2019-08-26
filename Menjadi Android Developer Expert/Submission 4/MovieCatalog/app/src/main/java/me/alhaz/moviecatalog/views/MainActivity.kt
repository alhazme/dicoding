package me.alhaz.moviecatalog.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import me.alhaz.moviecatalog.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.alhaz.moviecatalog.views.favorite.FavoriteFragment
import me.alhaz.moviecatalog.views.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var favoriteFragment: FavoriteFragment

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

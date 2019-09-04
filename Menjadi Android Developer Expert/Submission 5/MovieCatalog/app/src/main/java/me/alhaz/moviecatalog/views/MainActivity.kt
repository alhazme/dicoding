package me.alhaz.moviecatalog.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import me.alhaz.moviecatalog.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.alhaz.moviecatalog.provider.MovieProviderFragment
import me.alhaz.moviecatalog.reminder.SettingFragment
import me.alhaz.moviecatalog.views.favorite.FavoriteFragment
import me.alhaz.moviecatalog.views.home.HomeFragment
import me.alhaz.moviecatalog.views.movies.list.MovieListFragment
import me.alhaz.moviecatalog.views.tvshows.list.TVShowListFragment

class MainActivity : AppCompatActivity() {

//    private lateinit var homeFragment: HomeFragment
    private lateinit var movieListFragment: MovieListFragment
    private lateinit var tvShowListFragment: TVShowListFragment
    private lateinit var favoriteFragment: FavoriteFragment
    private lateinit var settingFragment: SettingFragment
//    private lateinit var movieProviderFragment: MovieProviderFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragments()
        bn_main.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setupFragments() {
//        homeFragment = HomeFragment()
        movieListFragment = MovieListFragment()
        tvShowListFragment = TVShowListFragment()
        favoriteFragment = FavoriteFragment()
        settingFragment = SettingFragment()
//        movieProviderFragment = MovieProviderFragment()

        movieListFragment?.let {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fl_container, it)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
//            R.id.home_menu -> {
//                homeFragment?.let {
//                    val manager = supportFragmentManager
//                    val transaction = manager.beginTransaction()
//                    transaction.replace(R.id.fl_container, it)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//                    return@OnNavigationItemSelectedListener true
//                }
//            }
            R.id.movie_menu -> {
                movieListFragment?.let {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fl_container, it)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            R.id.tvshow_menu -> {
                tvShowListFragment?.let {
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
            R.id.setting_menu -> {
                settingFragment?.let {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fl_container, it)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
//            R.id.provider_menu -> {
//                movieProviderFragment?.let {
//                    val manager = supportFragmentManager
//                    val transaction = manager.beginTransaction()
//                    transaction.replace(R.id.fl_container, it)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//                    return@OnNavigationItemSelectedListener true
//                }
//            }
        }
        false
    }

}

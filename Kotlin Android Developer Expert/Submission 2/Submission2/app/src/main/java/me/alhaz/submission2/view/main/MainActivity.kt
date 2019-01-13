package me.alhaz.submission2.view.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.alhaz.submission2.view.fragment.next.NextFragment
import me.alhaz.submission2.view.fragment.previous.PreviousFragment
import me.alhaz.submission2.R

class MainActivity : AppCompatActivity() {

    var previousFragment: PreviousFragment? = null
    var nextFragment: NextFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragments()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev -> {
                previousFragment?.let {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fragment_container, it)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next -> {
                nextFragment?.let {
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.fragment_container, it)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setupFragments() {

        previousFragment = PreviousFragment()
        nextFragment = NextFragment()

        previousFragment?.let {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment_container, it)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}

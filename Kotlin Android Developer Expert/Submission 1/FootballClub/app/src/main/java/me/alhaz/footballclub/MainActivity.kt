package me.alhaz.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    var footballClubs: ArrayList<FootballClub> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData();
        initView();
//        MainActivityUI(adapter).setContentView(this)
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        var desc = resources.getStringArray(R.array.club_description)
        footballClubs.clear()
        for (i in name.indices) {
            footballClubs.add(FootballClub(name[i],
                    image.getResourceId(i, 0), desc[i]))
        }

        //Recycle the typed array
        image.recycle()
    }

    /**
     *
     * Show List data to Recycler View
     *
     */


    private fun initView() {
        club_list.layoutManager = LinearLayoutManager(this)
        club_list.adapter = FootbalClubAdapter(footballClubs)
    }
}

/*
class MainActivityUI(val footballAdapter: FootbalClubAdapter) : AnkoComponent<MainActivity> {

    var recyclerView: RecyclerView? = null

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {

            layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)

            recyclerView = recyclerView {
                layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)
                layoutManager = LinearLayoutManager(context)
                adapter = footballAdapter

            }
        }
    }

}
*/
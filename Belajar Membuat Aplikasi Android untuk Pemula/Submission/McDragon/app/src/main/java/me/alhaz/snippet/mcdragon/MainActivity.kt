package me.alhaz.snippet.mcdragon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import android.content.Intent



class MainActivity : AppCompatActivity() {

    private lateinit var rvMenu: RecyclerView
    private var list: ArrayList<Menu> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLayout()
        setDefaultData()
    }

    private fun setupLayout() {
        rvMenu = findViewById(R.id.rv_menu)
        rvMenu.setHasFixedSize(true)
        rvMenu.layoutManager = LinearLayoutManager(this)
    }

    private fun setDefaultData() {
        list.addAll(MenuData.listData)
        val listMenuAdapter = ListMenuAdapter(list)
        listMenuAdapter.setOnItemClickCallback(object: ListMenuAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Menu) {
                showSelectedMenu(data)
            }
        })
        rvMenu.adapter = listMenuAdapter
    }

    private fun showSelectedMenu(menu: Menu) {
        val moveIntent = Intent(this, DetailActivity::class.java)
        moveIntent.putExtra("menu", menu)
        startActivity(moveIntent)
    }
}

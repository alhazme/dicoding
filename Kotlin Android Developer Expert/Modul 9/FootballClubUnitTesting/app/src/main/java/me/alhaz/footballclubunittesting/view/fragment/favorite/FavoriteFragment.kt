package me.alhaz.footballclubunittesting.view.fragment.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import me.alhaz.footballclubunittesting.R
import me.alhaz.footballclubunittesting.helper.Internet
import me.alhaz.footballclubunittesting.main.DetailActivity
import me.alhaz.footballclubunittesting.model.Database.Favorite
import me.alhaz.footballclubunittesting.model.Parser.Event
import me.alhaz.footballclubunittesting.presenter.favorite.FavoriteContract
import me.alhaz.footballclubunittesting.presenter.favorite.FavoritePresenter
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteFragment: Fragment(), FavoriteContract.View {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    val events: ArrayList<Event> = ArrayList()
    private lateinit var adapter: FavoriteAdapter
    var presenter: FavoriteContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.favorite_layout, container, false)

        setupView(view)
        setupPresenter()
        setupHandlerView()

        return view
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun setupView(view: View) {
        refreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refresh_layout)
        recyclerView = view.findViewById<RecyclerView>(R.id.list_layout)
        progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
    }

    private fun setupPresenter() {
        presenter = FavoritePresenter()
        presenter?.takeView(this)
    }

    private fun setupHandlerView() {
        refreshLayout.onRefresh {
            loadData()
        }
        recyclerView.layoutManager = LinearLayoutManager(activity!!.baseContext)
        loadData()

    }

    private fun loadData() {
        if (Internet().connectionAvailable(activity!!.baseContext)) {
            presenter?.loadEvent(activity!!.baseContext)
        }
        else {
            hideProgress()
            showAlert("Fail connect to server, please check your internet connection!")
        }
    }

    override fun showDatas(list: ArrayList<Favorite>) {
        adapter = FavoriteAdapter(activity!!.baseContext, list, {
            listClicked(it)
        })
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        refreshLayout.isRefreshing = false
    }

    private fun listClicked(favorite: Favorite) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("event_id", favorite.eventId)
        intent.putExtra("home_team_id", favorite.teamHomeId)
        intent.putExtra("away_team_id", favorite.teamAwayId)
        startActivity(intent)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        refreshLayout.isRefreshing = false
    }

    override fun showAlert(message: String?) {
        Toast.makeText(activity!!.baseContext, message!!, Toast.LENGTH_SHORT).show()
    }

}
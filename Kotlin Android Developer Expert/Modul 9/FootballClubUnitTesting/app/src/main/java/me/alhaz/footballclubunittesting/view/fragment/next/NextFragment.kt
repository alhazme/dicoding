package me.alhaz.footballclubunittesting.view.fragment.next

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import me.alhaz.footballclubunittesting.R
import me.alhaz.footballclubunittesting.helper.API.APIRepository
import me.alhaz.footballclubunittesting.helper.Internet
import me.alhaz.footballclubunittesting.main.DetailActivity
import me.alhaz.footballclubunittesting.model.Parser.Event
import me.alhaz.footballclubunittesting.presenter.next.NextContract
import me.alhaz.footballclubunittesting.presenter.next.NextPresenter
import org.jetbrains.anko.support.v4.onRefresh

class NextFragment: Fragment(), NextContract.View {

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    val events: ArrayList<Event> = ArrayList()
    private lateinit var adapter: NextAdapter
    var presenter: NextContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.next_layout, container, false)

        setupView(view)
        setupPresenter()
        setupHandlerView()

        return view
    }

    override fun onPause() {
        super.onPause()
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
        presenter = NextPresenter(APIRepository())
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
            presenter?.loadEvent("4328")
        }
        else {
            hideProgress()
            showAlert("Fail connect to server, please check your internet connection!")
        }
    }

    override fun showDatas(list: ArrayList<Event>) {
        activity?.let {
            val activity = it
            adapter = NextAdapter(activity.baseContext, list, {
                val event = it
                listClicked(event)
            })
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
            refreshLayout.isRefreshing = false
        }
    }

    private fun listClicked(event: Event) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("event_id", event.idEvent)
        intent.putExtra("home_team_id", event.idHomeTeam)
        intent.putExtra("away_team_id", event.idAwayTeam)
        startActivity(intent)
//        Toast.makeText(activity!!.baseContext, "Clicked: ${event.strHomeTeam}", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        refreshLayout.isRefreshing = false
    }

    override fun showAlert(message: String?) {
        activity?.let {
            val activity = it
            message?.let {
                Toast.makeText(activity.baseContext, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

}
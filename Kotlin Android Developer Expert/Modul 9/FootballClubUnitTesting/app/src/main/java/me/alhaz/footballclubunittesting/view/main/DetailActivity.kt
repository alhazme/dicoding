package me.alhaz.footballclubunittesting.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import me.alhaz.footballclubunittesting.R
import me.alhaz.footballclubunittesting.R.drawable.ic_add_to_favorites
import me.alhaz.footballclubunittesting.R.drawable.ic_added_to_favorites
import me.alhaz.footballclubunittesting.R.id.add_to_favorite
import me.alhaz.footballclubunittesting.R.menu.detail_menu
import me.alhaz.footballclubunittesting.helper.Internet
import me.alhaz.footballclubunittesting.model.Parser.Event
import me.alhaz.footballclubunittesting.model.Parser.Team
import me.alhaz.footballclubunittesting.presenter.detail.DetailContract
import me.alhaz.footballclubunittesting.presenter.detail.DetailPresenter
import org.jetbrains.anko.support.v4.onRefresh
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity(), DetailContract.View {

    var presenter: DetailContract.Presenter? = null
    var eventId: String = ""
    var teamHomeId: String = ""
    var teamAwayId: String = ""

    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var detailView: LinearLayout
    private lateinit var eventDateTextView: TextView
    private lateinit var teamHomeImageView: ImageView
    private lateinit var teamHomeNameTextView: TextView
    private lateinit var teamHomeFormationTextView: TextView
    private lateinit var teamHomeScoreTextView: TextView
    private lateinit var teamAwayImageView: ImageView
    private lateinit var teamAwayNameTextView: TextView
    private lateinit var teamAwayFormationTextView: TextView
    private lateinit var teamAwayScoreTextView: TextView
    private lateinit var eventHomeGoalTextView: TextView
    private lateinit var eventAwayGoalTextView: TextView
    private lateinit var eventHomeShotTextView: TextView
    private lateinit var eventAwayShotTextView: TextView
    private lateinit var eventHomeGoalKeeperTextView: TextView
    private lateinit var eventAwayGoalKeeperTextView: TextView
    private lateinit var eventHomeDefenceTextView: TextView
    private lateinit var eventAwayDefenceTextView: TextView
    private lateinit var eventHomeMidfieldTextView: TextView
    private lateinit var eventAwayMidfieldTextView: TextView
    private lateinit var eventHomeForwardTextView: TextView
    private lateinit var eventAwayForwardTextView: TextView
    private lateinit var eventHomeSubtitutesTextView: TextView
    private lateinit var eventAwaySubtitutesTextView: TextView
    private lateinit var progressBar: ProgressBar

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getDataFromIntent()
        setupPresenter()
        setupView()
        setupViewHandler()

        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getDataFromIntent() {
        eventId = intent.getStringExtra("event_id")
        teamHomeId = intent.getStringExtra("home_team_id")
        teamAwayId = intent.getStringExtra("away_team_id")
    }

    private fun setupPresenter() {
        presenter = DetailPresenter()
        presenter?.takeView(this)
    }

    private fun setupView() {
        refreshLayout = findViewById<SwipeRefreshLayout>(R.id.refresh_layout)
        detailView = findViewById<LinearLayout>(R.id.vw_detail)
        eventDateTextView = findViewById<TextView>(R.id.tv_date)
        teamHomeImageView = findViewById<ImageView>(R.id.iv_home)
        teamHomeNameTextView = findViewById<TextView>(R.id.tv_home)
        teamHomeFormationTextView = findViewById<TextView>(R.id.tv_home_formations)
        teamHomeScoreTextView = findViewById<TextView>(R.id.tv_home_score)
        teamAwayImageView = findViewById<ImageView>(R.id.iv_away)
        teamAwayNameTextView = findViewById<TextView>(R.id.tv_away)
        teamAwayFormationTextView = findViewById<TextView>(R.id.tv_away_formations)
        teamAwayScoreTextView = findViewById<TextView>(R.id.tv_away_score)
        eventHomeGoalTextView = findViewById<TextView>(R.id.tv_home_goals)
        eventAwayGoalTextView = findViewById<TextView>(R.id.tv_away_goals)
        eventHomeShotTextView = findViewById<TextView>(R.id.tv_home_shots)
        eventAwayShotTextView = findViewById<TextView>(R.id.tv_away_shots)
        eventHomeGoalKeeperTextView = findViewById<TextView>(R.id.tv_home_goalkeeper)
        eventAwayGoalKeeperTextView = findViewById<TextView>(R.id.tv_away_goalkeeper)
        eventHomeDefenceTextView = findViewById<TextView>(R.id.tv_home_defence)
        eventAwayDefenceTextView = findViewById<TextView>(R.id.tv_away_defence)
        eventHomeMidfieldTextView = findViewById<TextView>(R.id.tv_home_midfields)
        eventAwayMidfieldTextView = findViewById<TextView>(R.id.tv_away_midfields)
        eventHomeForwardTextView = findViewById<TextView>(R.id.tv_home_forward)
        eventAwayForwardTextView = findViewById<TextView>(R.id.tv_away_forward)
        eventHomeSubtitutesTextView = findViewById<TextView>(R.id.tv_home_subtitutes)
        eventAwaySubtitutesTextView = findViewById<TextView>(R.id.tv_away_subtitutes)
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
    }

    private fun setupViewHandler() {
        refreshLayout.onRefresh {
            loadEventData(eventId)
            loadTeamData(teamHomeId, "home")
            loadTeamData(teamAwayId, "away")
        }

        loadEventData(eventId)
        loadTeamData(teamHomeId, "home")
        loadTeamData(teamAwayId, "away")

    }

    private fun loadEventData(id: String) {
        refreshLayout.isRefreshing = false
        if (Internet().connectionAvailable(this)) {
            presenter?.loadEvent(id)
        }
        else {
            hideProgress()
            showAlert("Fail connect to server, please check your internet connection!")
        }
    }

    private fun loadTeamData(id: String, type: String) {
        refreshLayout.isRefreshing = false
        if (Internet().connectionAvailable(this)) {
            presenter?.loadTeam(id, type)
        }
        else {
            hideProgress()
            showAlert("Fail connect to server, please check your internet connection!")
        }
    }

    override fun showEventData(list: ArrayList<Event>) {
        if (list.size == 1) {
            val event: Event = list.first()

            eventDateTextView.text = event.strDate
            teamHomeNameTextView.text = event.strHomeTeam
            teamHomeFormationTextView.text = event.strHomeFormation
            if (event.intHomeScore != null) {
                teamHomeScoreTextView.text = event.intHomeScore.toString()
            }
            else {
                teamHomeScoreTextView.text =""
            }
            teamAwayNameTextView.text = event.strAwayTeam
            teamAwayFormationTextView.text = event.strAwayFormation
            if (event.intAwayScore != null) {
                teamAwayScoreTextView.text = event.intAwayScore.toString()
            }
            else {
                teamAwayScoreTextView.text = ""
            }
            eventHomeGoalTextView.text = event.strHomeGoalDetails
            eventAwayGoalTextView.text = event.strAwayGoalDetails
            if (event.intHomeShots != null) {
                eventHomeShotTextView.text =  event.intHomeShots.toString()
            }
            else {
                eventHomeShotTextView.text = ""
            }
            if (event.intAwayShots != null) {
                eventAwayShotTextView.text = event.intAwayShots.toString()
            }
            else {
                eventAwayShotTextView.text = ""
            }
            eventHomeGoalKeeperTextView.text = event.strHomeLineupGoalkeeper
            eventAwayGoalKeeperTextView.text = event.strAwayLineupGoalkeeper
            eventHomeDefenceTextView.text = event.strHomeLineupDefense
            eventAwayDefenceTextView.text = event.strAwayLineupDefense
            eventHomeMidfieldTextView.text = event.strHomeLineupMidfield
            eventAwayMidfieldTextView.text = event.strAwayLineupMidfield
            eventHomeForwardTextView.text = event.strHomeLineupForward
            eventAwayForwardTextView.text = event.strAwayLineupForward
            eventHomeSubtitutesTextView.text = event.strHomeLineupSubstitutes
            eventAwaySubtitutesTextView.text = event.strAwayLineupSubstitutes
        }
        else {

        }
    }

    override fun showEventTeam(list: ArrayList<Team>, type: String) {
        if (list.size == 1) {
            val team: Team = list.first()
            if (type == "away") {
                if (!isDestroyed) Glide.with(this).load(team.strTeamBadge).into(teamAwayImageView)
            }
            else {
                if (!isDestroyed) Glide.with(this).load(team.strTeamBadge).into(teamHomeImageView)
            }
        }
        else {

        }
    }

    override fun showAlert(message: String?) {
        message?.let {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showProgress() {
        detailView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        detailView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    private fun favoriteState() {
        presenter?.let {
            var favorite = it.isFavorite(this, eventId)
            isFavorite = if (favorite != null) favorite else false
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite() {
        var homeScore = 0
        if (teamHomeScoreTextView.text.toString().length > 0) {
            homeScore = teamHomeScoreTextView.text.toString().toInt()
        }
        var awayScore = 0
        if (teamAwayScoreTextView.text.toString().length > 0) {
            awayScore = teamAwayScoreTextView.text.toString().toInt()
        }
        presenter?.addToFavorite(
                this,
                eventId,
                eventDateTextView.text.toString(),
                teamHomeId,
                teamHomeNameTextView.text.toString(),
                homeScore,
                teamAwayId,
                teamAwayNameTextView.text.toString(),
                awayScore
        )
    }

    private fun removeFromFavorite() {
        presenter?.removeFromFavorite(this, eventId)
    }

}
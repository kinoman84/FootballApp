package ru.alexeybuchnev.football.presentation.teamdetails

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.di.FootballApplication
import ru.alexeybuchnev.football.domain.entity.Team
import ru.alexeybuchnev.football.presentation.ViewModelFactory
import java.lang.IllegalArgumentException
import javax.inject.Inject

class TeamDetailsFragment : Fragment(R.layout.fragment_team_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as FootballApplication).component
    }

    private val teamDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[TeamDetailsViewModel::class.java]
    }

    private var selectedTeamId: Int? = null
    private var playerButtonCallback: PlayerButtonCallback? = null

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)

        if (context is PlayerButtonCallback) {
            playerButtonCallback = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedTeamId = it.getInt(ARG_TEAM_ID)
        }
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedTeamId?.let {
            teamDetailsViewModel.getTeamDetails(it).observe(this.viewLifecycleOwner) {
                updateUi(it, view)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mein_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open_players_list -> {
                playerButtonCallback?.routeToPlayerList(
                    teamId = selectedTeamId ?: throw IllegalArgumentException()
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateUi(team: Team, view: View) {
        view.findViewById<TextView>(R.id.team_name_title_text_view)?.text = team.name

        val foundedTextView = view.findViewById<TextView>(R.id.founding_year_text_view)
        if (team.founded != null) {
            foundedTextView.text = String.format(
                requireContext().resources.getString(R.string.founding_year),
                team.founded
            )
        } else {
            foundedTextView.visibility = View.GONE
        }

        view.findViewById<TextView>(R.id.stadium_name_text_view)?.text = team.venue?.name

        view.findViewById<TextView>(R.id.stadium_address_text_view)?.text = String.format(
            requireContext().resources.getString(R.string.stadium_address),
            team.venue?.address
        )

        view.findViewById<TextView>(R.id.stadium_city_text_view)?.text = String.format(
            requireContext().resources.getString(R.string.stadium_city),
            team.venue?.city
        )

        view.findViewById<TextView>(R.id.stadium_capacity_text_view)?.text = String.format(
            requireContext().resources.getString(R.string.stadium_capacity),
            team.venue?.capacity
        )

        view.findViewById<ImageView>(R.id.team_logo_image_view)?.load(team.logoUrl) {
            placeholder(R.drawable.ic_baseline_sports_soccer_24)
            error(R.drawable.ic_baseline_sports_soccer_24)
        }

        view.findViewById<ImageView>(R.id.stadium_photo_image_view)?.load(team.venue?.imageUrl) {
            placeholder(R.drawable.ic_baseline_stadium_24)
            error(R.drawable.ic_baseline_stadium_24)
        }

        (activity as? AppCompatActivity)?.supportActionBar?.title = team.name
    }

    override fun onDetach() {
        playerButtonCallback = null

        super.onDetach()
    }

    interface PlayerButtonCallback {
        fun routeToPlayerList(teamId: Int)
    }

    companion object {

        private const val ARG_TEAM_ID = "selectedTeamId"

        fun newInstance(teamId: Int) = TeamDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_TEAM_ID, teamId)
            }
        }
    }
}
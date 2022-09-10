package ru.alexeybuchnev.football.presentation.teamdetails

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import kotlinx.coroutines.runBlocking
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.data.TeamRepositoryMockupImpl
import ru.alexeybuchnev.football.model.Team
import java.lang.IllegalArgumentException

class TeamDetailsFragment : Fragment(R.layout.fragment_team_details) {

    private var selectedTeamId: Int? = null
    private var playerButtonCallback: PlayerButtonCallback? = null
    private lateinit var teamDetailsViewModel: TeamDetailsViewModel

    override fun onAttach(context: Context) {
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamDetailsViewModel = ViewModelProvider(this) [TeamDetailsViewModel::class.java]

        teamDetailsViewModel.teamDetailsLiveData.observe(this.viewLifecycleOwner) {
            updateUi(it, view)
        }

        selectedTeamId?.let {
            teamDetailsViewModel.loadTeamDetails(it)
        }
    }

    private fun updateUi(team: Team, view: View) {
        view.findViewById<TextView>(R.id.team_name_title_text_view)?.text = team.name

        view.findViewById<TextView>(R.id.founding_year_text_view)?.text = String.format(
            requireContext().resources.getString(R.string.founding_year),
            team.founded
        )

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

        view.findViewById<Button>(R.id.route_to_players_list_button)?.setOnClickListener {
            playerButtonCallback?.routeToPlayerList(team.id)
        }

        view.findViewById<ImageView>(R.id.team_logo_image_view)?.load(team.logoUrl) {
            placeholder(R.drawable.ic_baseline_sports_soccer_24)
            error(R.drawable.ic_baseline_sports_soccer_24)
        }

        view.findViewById<ImageView>(R.id.stadium_photo_image_view)?.load(team.venue?.imageUrl) {
            placeholder(R.drawable.ic_baseline_stadium_24)
            error(R.drawable.ic_baseline_stadium_24)
        }
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
package ru.alexeybuchnev.football.presentation.palyerslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.model.Player

class PlayersListFragment : Fragment(R.layout.fragment_players_list) {

    private lateinit var playersRecyclerView: RecyclerView
    private lateinit var playerViewModel: PlayersListViewModel

    private var selectedTeamId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            selectedTeamId = it.getInt(ARG_TEAM_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playersRecyclerView = view.findViewById(R.id.players_list_recycler_view)
        playersRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        playersRecyclerView.adapter = PlayersListAdapter()

        playerViewModel = ViewModelProvider(this)[PlayersListViewModel::class.java]

        playerViewModel.playersListLiveData.observe(this.viewLifecycleOwner) {
            updateUi(players = it)
        }

        selectedTeamId?.let {
            playerViewModel.loadPlayers(it)
        }
    }

    private fun updateUi(players: List<Player>) {
        (playersRecyclerView.adapter as PlayersListAdapter).setList(players)
    }

    companion object {

        private const val ARG_TEAM_ID = "selectedTeamId"

        fun newInstance(teamId: Int) = PlayersListFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_TEAM_ID, teamId)
            }
        }
    }
}
package ru.alexeybuchnev.football.presentation.palyerslist

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.di.FootballApplication
import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.presentation.ViewModelFactory
import javax.inject.Inject

class PlayersListFragment : Fragment(R.layout.fragment_players_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as FootballApplication).component
    }

    private val playerViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PlayersListViewModel::class.java]
    }

    private lateinit var playersRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var selectedTeamId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        arguments?.let {
            selectedTeamId = it.getInt(ARG_TEAM_ID)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)
        playersRecyclerView = view.findViewById(R.id.players_list_recycler_view)
        playersRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        playersRecyclerView.adapter = PlayersListAdapter()

        playerViewModel.playersStateListLiveData.observe(this.viewLifecycleOwner) { state ->
            when (state) {
                is PlayersListViewModel.PlayersListViewState.PlayersLoading -> {
                    setLoading(true)
                }
                is PlayersListViewModel.PlayersListViewState.PlayersLoaded -> {
                    setLoading(false)
                    updateUi(state.players)
                }
                is PlayersListViewModel.PlayersListViewState.Error -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), state.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        selectedTeamId?.let {
            playerViewModel.loadPlayers(it)
        }
    }

    private fun updateUi(players: List<Player>) {
        (playersRecyclerView.adapter as PlayersListAdapter).submitList(players)
    }

    private fun setLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.GONE
        }

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
package ru.alexeybuchnev.football.presentation.teams

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.model.Team

class TeamsListFragment : Fragment(R.layout.fragment_teams_list) {

    private lateinit var teamsRecyclerView: RecyclerView
    private lateinit var teamsViewModel: TeamListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        teamsRecyclerView = view.findViewById(R.id.teams_list_recycler_View)
        teamsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        teamsRecyclerView.adapter = TeamsListAdapter()

        teamsViewModel = ViewModelProvider(this)[TeamListViewModel::class.java]

        teamsViewModel.teamLiveData.observe(this.viewLifecycleOwner) {
            updateUi(it)
        }

        teamsViewModel.loadTeams()

    }

    private fun updateUi(teams: List<Team>) {
        (teamsRecyclerView.adapter as TeamsListAdapter).setList(teams)
    }
}
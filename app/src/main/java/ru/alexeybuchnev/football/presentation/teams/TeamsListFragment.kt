package ru.alexeybuchnev.football.presentation.teams

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.data.TeamRepositoryImpl

class TeamsListFragment : Fragment(R.layout.fragment_teams_list) {

    private lateinit var teamsRecyclerView: RecyclerView
    private lateinit var adapter: TeamsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        teamsRecyclerView = view.findViewById(R.id.teams_list_recycler_View)
        teamsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = TeamsListAdapter()

        val list = TeamRepositoryImpl().getTeams()

        teamsRecyclerView.adapter = adapter
        (teamsRecyclerView.adapter as TeamsListAdapter).setList(list)

    }
}
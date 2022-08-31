package ru.alexeybuchnev.football.presentation.teams

import android.content.Context
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

    private var teamClickListener: TeamListItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TeamListItemClickListener) {
            teamClickListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        teamsRecyclerView = view.findViewById(R.id.teams_list_recycler_View)
        teamsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        //TODO ещё раз про лямбды почитать
        teamsRecyclerView.adapter = TeamsListAdapter {
            teamId -> teamClickListener?.onTeamSelected(teamId)
        }

        teamsViewModel = ViewModelProvider(this)[TeamListViewModel::class.java]

        teamsViewModel.teamLiveData.observe(this.viewLifecycleOwner) {
            updateUi(it)
        }

        teamsViewModel.loadTeams()

    }

    private fun updateUi(teams: List<Team>) {
        (teamsRecyclerView.adapter as TeamsListAdapter).setList(teams)
    }

    interface TeamListItemClickListener {
        fun onTeamSelected(teamId: Int)
    }

    companion object {
        fun newInstance() = TeamsListFragment()
    }
}
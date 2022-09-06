package ru.alexeybuchnev.football.presentation.teams

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.model.Team

class TeamsListFragment : Fragment(R.layout.fragment_teams_list) {

    private lateinit var teamsRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var teamsViewModel: TeamListViewModel

    private var teamClickListener: TeamListItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TeamListItemClickListener) {
            teamClickListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        progressBar = view.findViewById(R.id.progress_bar)
        teamsRecyclerView = view.findViewById(R.id.teams_list_recycler_View)
        teamsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        //TODO ещё раз про лямбды почитать
        teamsRecyclerView.adapter = TeamsListAdapter {
            teamId -> teamClickListener?.onTeamSelected(teamId)
        }

        teamsViewModel = ViewModelProvider(this)[TeamListViewModel::class.java]

        teamsViewModel.teamsViewStateLiveData.observe(this.viewLifecycleOwner) { state ->
            when (state) {
                is TeamListViewModel.TeamsListViewState.TeamsLoaded -> {
                    setLoading(false)
                    bindData(state.teams)
                }
                is TeamListViewModel.TeamsListViewState.TeamsLoading -> {
                    setLoading(true)
                }
                is TeamListViewModel.TeamsListViewState.Error -> {
                    TODO()
                }
            }
        }

        teamsViewModel.loadTeams()

    }

    override fun onDetach() {
        teamClickListener = null

        super.onDetach()
    }

    private fun bindData(teams: List<Team>) {
        (teamsRecyclerView.adapter as TeamsListAdapter).setList(teams)
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    interface TeamListItemClickListener {
        fun onTeamSelected(teamId: Int)
    }

    companion object {
        fun newInstance() = TeamsListFragment()
    }
}
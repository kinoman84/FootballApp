package ru.alexeybuchnev.football.presentation.teams

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.di.FootballApplication
import ru.alexeybuchnev.football.domain.entity.Team
import ru.alexeybuchnev.football.presentation.ViewModelFactory
import javax.inject.Inject

class TeamsListFragment : Fragment(R.layout.fragment_teams_list) {

    private val component by lazy {
        (requireActivity().application as FootballApplication).component
    }

    private lateinit var teamsRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var teamClickListener: TeamListItemClickListener? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val teamsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[TeamListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)

        if (context is TeamListItemClickListener) {
            teamClickListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as? AppCompatActivity)?.supportActionBar?.title = "Football"

        progressBar = view.findViewById(R.id.progress_bar)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)
        teamsRecyclerView = view.findViewById(R.id.teams_list_recycler_View)

        teamsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        teamsRecyclerView.adapter = TeamsListAdapter { teamId ->
            teamClickListener?.onTeamSelected(teamId)
        }

        swipeRefresh.setOnRefreshListener {
            refresh()
        }

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
                    setLoading(false)
                    Toast.makeText(requireContext(), state.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        teamsViewModel.loadData()

    }

    override fun onDetach() {
        teamClickListener = null

        super.onDetach()
    }

    private fun bindData(teams: List<Team>) {
        (teamsRecyclerView.adapter as TeamsListAdapter).submitList(teams)
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
            swipeRefresh.isRefreshing = false
        }
    }

    private fun refresh() {
        teamsViewModel.refreshData()
    }

    interface TeamListItemClickListener {
        fun onTeamSelected(teamId: Int)
    }

    companion object {
        fun newInstance() = TeamsListFragment()
    }
}
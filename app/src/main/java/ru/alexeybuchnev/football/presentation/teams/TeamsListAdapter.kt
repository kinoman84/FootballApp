package ru.alexeybuchnev.football.presentation.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.domain.entity.Team

class TeamsListAdapter(private val onTeamClick: (teamId: Int) -> Unit) :
    RecyclerView.Adapter<TeamsListItemViewHolder>() {
    private var teamList: List<Team> = listOf()

    fun setList(list: List<Team>) {
        teamList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsListItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_teams_list, parent, false)
        return TeamsListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsListItemViewHolder, position: Int) {
        holder.bind(teamList[position], onTeamClick)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

}
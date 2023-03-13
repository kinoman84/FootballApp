package ru.alexeybuchnev.football.presentation.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.domain.entity.Team

class TeamsListAdapter(private val onTeamClick: (teamId: Int) -> Unit) :
    ListAdapter<Team, TeamsListItemViewHolder>(TeamItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsListItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_teams_list, parent, false)
        return TeamsListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsListItemViewHolder, position: Int) {
        holder.bind(getItem(position), onTeamClick)
    }

}
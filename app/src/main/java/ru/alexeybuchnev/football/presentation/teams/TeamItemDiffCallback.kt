package ru.alexeybuchnev.football.presentation.teams

import androidx.recyclerview.widget.DiffUtil
import ru.alexeybuchnev.football.domain.entity.Team

class TeamItemDiffCallback: DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
        return oldItem == newItem
    }
}
package ru.alexeybuchnev.football.presentation.palyerslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.domain.entity.Player

class PlayersListAdapter : ListAdapter<Player, PlayersListViewHolder>(PlayerItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_players_list, parent, false)
        return PlayersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayersListViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player)
    }

}
package ru.alexeybuchnev.football.presentation.palyerslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.model.Player

class PlayersListAdapter : RecyclerView.Adapter<PlayersListViewHolder>() {

    private var players: List<Player> = listOf()

    fun setList(list: List<Player>) {
        players = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_players_list, parent, false)
        return PlayersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayersListViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int {
        return players.size
    }
}
package ru.alexeybuchnev.football.presentation.palyerslist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.model.Player

class PlayersListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.player_name_text_view)
    private val numberTextView: TextView = itemView.findViewById(R.id.player_number_text_view)
    private val positionTextView: TextView = itemView.findViewById(R.id.player_position_text_view)

    fun bind(player: Player) {
        nameTextView.text = player.name
        numberTextView.text = String.format(
            itemView.context.resources.getString(R.string.player_number),
            player.number
        )
        positionTextView.text = String.format(
            itemView.context.resources.getString(R.string.player_position),
            player.position
        )
    }
}
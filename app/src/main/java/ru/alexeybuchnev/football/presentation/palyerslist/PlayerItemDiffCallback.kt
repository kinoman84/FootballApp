package ru.alexeybuchnev.football.presentation.palyerslist

import androidx.recyclerview.widget.DiffUtil
import ru.alexeybuchnev.football.domain.entity.Player

class PlayerItemDiffCallback: DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}
package ru.alexeybuchnev.football.presentation.palyerslist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.model.Player

class PlayersListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.player_name_text_view)
    private val numberTextView: TextView = itemView.findViewById(R.id.player_number_text_view)
    private val positionTextView: TextView = itemView.findViewById(R.id.player_position_text_view)
    private val photoImageView: ImageView = itemView.findViewById(R.id.player_photo_image_view)

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
        photoImageView.load(player.photoUrl) {
            placeholder(R.drawable.ic_baseline_face_24)
            error(R.drawable.ic_baseline_face_24)
        }
    }
}
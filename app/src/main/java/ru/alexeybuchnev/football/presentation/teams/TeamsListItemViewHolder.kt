package ru.alexeybuchnev.football.presentation.teams

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.model.Team

class TeamsListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val teamLogoImageView: ImageView = itemView.findViewById(R.id.team_logo_image_view)
    private val teamNameTextView: TextView = itemView.findViewById(R.id.team_name_text_view)

    fun bind(team: Team, onTeamClick: (teamId: Int) -> Unit) {
        teamNameTextView.text = team.name
        itemView.setOnClickListener {
            onTeamClick(team.id) }
    }
}
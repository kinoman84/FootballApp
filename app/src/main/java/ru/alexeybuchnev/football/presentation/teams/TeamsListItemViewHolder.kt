package ru.alexeybuchnev.football.presentation.teams

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.alexeybuchnev.football.R
import ru.alexeybuchnev.football.domain.entity.Team

class TeamsListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val teamLogoImageView: ImageView = itemView.findViewById(R.id.team_logo_image_view)
    private val teamNameTextView: TextView = itemView.findViewById(R.id.team_name_text_view)

    fun bind(team: Team, onTeamClick: (teamId: Int) -> Unit) {
        teamNameTextView.text = team.name
        itemView.setOnClickListener {
            onTeamClick(team.id) }
        teamLogoImageView.load(team.logoUrl) {
            placeholder(R.drawable.ic_baseline_sports_soccer_24)
            error(R.drawable.ic_baseline_sports_soccer_24)
        }
    }
}
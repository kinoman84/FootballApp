package ru.alexeybuchnev.football.domain.repository

import androidx.lifecycle.LiveData
import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.domain.entity.Team

interface TeamRepository {
    suspend fun getTeams() : List<Team>
    suspend fun getTeamsCash() : List<Team>
    suspend fun getTeam(teamId: Int) : Team
    suspend fun getPlayers(teamId: Int): List<Player>
}
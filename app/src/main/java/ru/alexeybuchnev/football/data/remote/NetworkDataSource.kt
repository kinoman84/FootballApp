package ru.alexeybuchnev.football.data.remote

import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.domain.entity.Team

interface NetworkDataSource {
    suspend fun getTeams() : List<Team>
    suspend fun getTeam(teamId: Int) : Team
    suspend fun getPlayers(teamId: Int) : List<Player>
}
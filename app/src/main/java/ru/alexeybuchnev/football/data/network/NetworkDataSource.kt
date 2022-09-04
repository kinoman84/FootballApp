package ru.alexeybuchnev.football.data.network

import ru.alexeybuchnev.football.model.Player
import ru.alexeybuchnev.football.model.Team

interface NetworkDataSource {
    suspend fun getTeams() : List<Team>
    suspend fun getTeam(teamId: Int) : Team
    suspend fun getPlayers(teamId: Int) : List<Player>
}
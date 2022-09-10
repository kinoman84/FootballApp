package ru.alexeybuchnev.football.data

import ru.alexeybuchnev.football.model.Player
import ru.alexeybuchnev.football.model.Team

interface TeamRepository {
    suspend fun getTeams() : List<Team>
    suspend fun getTeamsCash() : List<Team>
    suspend fun getTeam(teamId: Int) : Team
    suspend fun getPlayers(teamId: Int): List<Player>
}
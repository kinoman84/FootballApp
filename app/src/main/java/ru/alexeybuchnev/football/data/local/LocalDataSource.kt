package ru.alexeybuchnev.football.data.local

import ru.alexeybuchnev.football.model.Player
import ru.alexeybuchnev.football.model.Team

interface LocalDataSource {
    suspend fun getTeams() : List<Team>
    suspend fun getTeam(teamId: Int) : Team?
    suspend fun saveTeams(teams: List<Team>)
}
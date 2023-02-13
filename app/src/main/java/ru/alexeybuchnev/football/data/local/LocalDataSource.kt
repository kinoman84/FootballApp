package ru.alexeybuchnev.football.data.local

import ru.alexeybuchnev.football.domain.entity.Team

interface LocalDataSource {
    suspend fun getTeams() : List<Team>
    suspend fun getTeam(teamId: Int) : Team?
    suspend fun saveTeams(teams: List<Team>)
}
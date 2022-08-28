package ru.alexeybuchnev.football.data

import ru.alexeybuchnev.football.model.Player
import ru.alexeybuchnev.football.model.Team

interface TeamRepository {
    fun getTeams() : List<Team>
    fun getTeam(teamId: Int) : Team
    fun getPlayers(teamId: Int): List<Player>
}
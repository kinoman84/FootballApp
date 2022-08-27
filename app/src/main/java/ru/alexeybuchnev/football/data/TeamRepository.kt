package ru.alexeybuchnev.football.data

import ru.alexeybuchnev.football.model.Team

interface TeamRepository {
    fun getTeams() : List<Team>
}
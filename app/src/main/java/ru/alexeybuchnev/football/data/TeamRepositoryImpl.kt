package ru.alexeybuchnev.football.data

import ru.alexeybuchnev.football.model.Team

class TeamRepositoryImpl : TeamRepository {

    override fun getTeams(): List<Team> {
        return listOf(
            Team("Loko", "url"),
            Team("Spartak", "url"),
            Team("CSKA", "url")
        )
    }

}
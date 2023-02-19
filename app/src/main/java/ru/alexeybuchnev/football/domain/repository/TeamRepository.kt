package ru.alexeybuchnev.football.domain.repository

import androidx.lifecycle.LiveData
import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.domain.entity.Team

interface TeamRepository {
    fun getTeams(): LiveData<List<Team>>
    fun getTeam(teamId: Int): LiveData<Team>
    suspend fun getPlayers(teamId: Int): List<Player>
    suspend fun updateData()
}
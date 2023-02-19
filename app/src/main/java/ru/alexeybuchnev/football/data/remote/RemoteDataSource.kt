package ru.alexeybuchnev.football.data.remote

import ru.alexeybuchnev.football.data.remote.retrofit.dto.PlayerDto
import ru.alexeybuchnev.football.data.remote.retrofit.dto.PlayerListDto
import ru.alexeybuchnev.football.data.remote.retrofit.dto.TeamItemDto

interface RemoteDataSource {
    suspend fun getTeams() : List<TeamItemDto>
    suspend fun getPlayers(teamId: Int) : List<PlayerDto>
}
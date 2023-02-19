package ru.alexeybuchnev.football.data.remote.retrofit

import retrofit2.http.*
import ru.alexeybuchnev.football.data.remote.retrofit.dto.PlayerResponseDto
import ru.alexeybuchnev.football.data.remote.retrofit.dto.TeamResponseDto

/**
 * api дакументация https://www.api-football.com/documentation-v3#section/Introduction
 */

interface FootballApiService {

    @GET("teams")
    suspend fun getTeams(
        @HeaderMap headers: Map<String, String>,
        @Query("country") country: String,
        @Query("season") season: Int,
        @Query("league") league: Int
    ): TeamResponseDto

    @GET("players/squads")
    suspend fun getPlayers(
        @HeaderMap headers: Map<String, String>,
        @Query("team") team: Int
    ): PlayerResponseDto
}
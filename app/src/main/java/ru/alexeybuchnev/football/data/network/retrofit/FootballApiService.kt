package ru.alexeybuchnev.football.data.network.retrofit

import retrofit2.http.*
import ru.alexeybuchnev.football.data.network.retrofit.response.PlayersResponse
import ru.alexeybuchnev.football.data.network.retrofit.response.TeamsResponse

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
    ): TeamsResponse

    @GET("players/squads")
    suspend fun getPlayers(
        @HeaderMap headers: Map<String, String>,
        @Query("team") team: Int
    ): PlayersResponse
}
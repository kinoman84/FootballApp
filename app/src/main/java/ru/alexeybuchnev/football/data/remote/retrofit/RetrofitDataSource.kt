package ru.alexeybuchnev.football.data.remote.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import ru.alexeybuchnev.football.data.remote.NetworkDataSource
import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.domain.entity.Team
import ru.alexeybuchnev.football.domain.entity.Venue
import java.util.NoSuchElementException
import java.util.concurrent.TimeUnit

class RetrofitDataSource : NetworkDataSource {

    private val headers = mapOf(
        "x-rapidapi-key" to "f7004386ac3802c6c8a81d52ab505ae8",
        "x-rapidapi-host" to "v3.football.api-sports.io")
    private val country = "Russia"
    private val season = 2022
    private val league = 235

    private var teamCash: List<Team> = emptyList()

    override suspend fun getTeams(): List<Team> {
        if (teamCash.isEmpty()) {
            teamCash = teamCash.plus(loadTeamsList())
        }

        return teamCash
    }

    override suspend fun getTeam(teamId: Int): Team {
        if (teamCash.isEmpty()) {
            teamCash = teamCash.plus(loadTeamsList())
        }

        return teamCash.find { it.id == teamId } ?: throw NoSuchElementException()
    }

    override suspend fun getPlayers(teamId: Int): List<Player> {
        val response = RetrofitModule.api.getPlayers(headers, teamId)

        if (response.response.isEmpty()) {
            throw throw NoSuchElementException("Error")
        }

        val players = response.response.first().players.map {
            Player(
                id = it.id,
                name = it.name,
                age = it.age,
                number = it.number,
                position = it.position,
                photoUrl = it.photoUrl
            )
        }

        return players
    }

    private suspend fun loadTeamsList(): List<Team> {


        val response = RetrofitModule.api.getTeams(
            headers,
            country,
            season,
            league
        )

        if (response.response.isEmpty()) {
            throw NoSuchElementException("No teams")
        }

        val teams = response.response.map {
            Team(
                id = it.teamData.id,
                name = it.teamData.name,
                founded = it.teamData.founded,
                logoUrl = it.teamData.logoUrl,
                venue = Venue(
                    id = it.venueData.id,
                    name = it.venueData.name,
                    address = it.venueData.address.mnemonicToChar(),
                    city = it.venueData.city,
                    capacity = it.venueData.capacity,
                    imageUrl = it.venueData.imageUrl
                )
            )
        }

        return teams
    }

    /**
     * В некоторых случаях текст содержал HTML мнемоники типа &apos; функция их уберает.
     * + можно будет быстро добавить замену других мнемоников при необходимости
     */
    private fun String.mnemonicToChar() : String {

        var clearedString = this

        val mnemonics = mapOf(
            "&apos;" to "'"
        )

        for (mnemonic in mnemonics) {
            clearedString = clearedString.replace(mnemonic.key, mnemonic.value)
        }

        return clearedString
    }


}

private object RetrofitModule {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    private val client: OkHttpClient = OkHttpClient()
        .newBuilder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private const val baseUrl = "https://v3.football.api-sports.io/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val api: FootballApiService = retrofit.create()
}
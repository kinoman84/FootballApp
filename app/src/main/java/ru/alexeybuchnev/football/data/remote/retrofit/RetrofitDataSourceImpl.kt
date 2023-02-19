package ru.alexeybuchnev.football.data.remote.retrofit

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import ru.alexeybuchnev.football.data.remote.RemoteDataSource
import ru.alexeybuchnev.football.data.remote.retrofit.dto.PlayerDto
import ru.alexeybuchnev.football.data.remote.retrofit.dto.TeamItemDto
import java.util.concurrent.TimeUnit

class RetrofitDataSourceImpl : RemoteDataSource {

    private val headers = mapOf(
        KEY_HEADER_NAME to KEY_HEADER_VALUE,
        HOST_HEADER_NAME to HOST_HEADER_VALUE
    )
    private val country = COUNTRY
    private val season = SEASON
    private val league = LEAGUE

    override suspend fun getTeams(): List<TeamItemDto> {
        val data = RetrofitModule.api.getTeams(
            headers,
            country,
            season,
            league
        )
        Log.d("RetrofitDataSourceImpl", "result $data")

        return data.response
    }

    override suspend fun getPlayers(teamId: Int): List<PlayerDto> {
        val data = RetrofitModule.api.getPlayers(headers, teamId)

        return data.response.first().players
    }

    companion object {
        private const val KEY_HEADER_NAME = "x-rapidapi-key"
        private const val KEY_HEADER_VALUE = "f7004386ac3802c6c8a81d52ab505ae8"
        private const val HOST_HEADER_NAME = "x-rapidapi-host"
        private const val HOST_HEADER_VALUE = "v3.football.api-sports.io"
        private const val COUNTRY = "Russia"
        private const val SEASON = 2022
        private const val LEAGUE = 235
    }
}

private object RetrofitModule {

    private const val BASE_URL = "https://v3.football.api-sports.io/"

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

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val api: FootballApiService = retrofit.create()
}
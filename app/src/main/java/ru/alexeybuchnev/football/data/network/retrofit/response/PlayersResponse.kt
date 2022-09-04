package ru.alexeybuchnev.football.data.network.retrofit.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayersResponse(
    @SerialName("response") val response: List<PlayersResponseData>
)

@Serializable
data class PlayersResponseData(
    @SerialName("players") val players: List<PlayerResponseData>
)

@Serializable
data class PlayerResponseData(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("age") val age: Int,
    @SerialName("number") val number: Int,
    @SerialName("position") val position: String,
    @SerialName("photo") val photoUrl: String
)
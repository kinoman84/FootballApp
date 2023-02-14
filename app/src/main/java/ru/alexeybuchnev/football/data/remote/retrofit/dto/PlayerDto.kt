package ru.alexeybuchnev.football.data.remote.retrofit.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponseDto(
    @SerialName("response") val response: List<PlayerListDto>
)

@Serializable
data class PlayerListDto(
    @SerialName("players") val players: List<PlayerDto>
)

@Serializable
data class PlayerDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("age") val age: Int,
    @SerialName("number") val number: Int? = null,
    @SerialName("position") val position: String,
    @SerialName("photo") val photoUrl: String
)
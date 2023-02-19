package ru.alexeybuchnev.football.data.remote.retrofit.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponseDto (
    @SerialName("response") val response: List<TeamItemDto>,
)

@Serializable
data class TeamItemDto(
    @SerialName("team") val teamData: TeamInfoDto,
    @SerialName("venue") val venueData: VenueDto
)

@Serializable
data class TeamInfoDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("founded") val founded: Int? = null,
    @SerialName("logo") val logoUrl: String
)

@Serializable
data class VenueDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("address") val address: String,
    @SerialName("city") val city: String,
    @SerialName("capacity") val capacity: Int,
    @SerialName("image") val imageUrl: String
)
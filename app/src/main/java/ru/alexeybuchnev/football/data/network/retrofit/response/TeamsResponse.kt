package ru.alexeybuchnev.football.data.network.retrofit.response

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamsResponse (
    @SerialName("response") val response: List<TeamResponse>,
)

@Serializable
data class TeamResponse(
    @SerialName("team") val teamData: TeamResponseData,
    @SerialName("venue") val venueData: VenueResponseData
)

@Serializable
data class TeamResponseData(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("founded") val founded: Int? = null,
    @SerialName("logo") val logoUrl: String
)

@Serializable
data class VenueResponseData(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("address") val address: String,
    @SerialName("city") val city: String,
    @SerialName("capacity") val capacity: Int,
    @SerialName("image") val imageUrl: String
)
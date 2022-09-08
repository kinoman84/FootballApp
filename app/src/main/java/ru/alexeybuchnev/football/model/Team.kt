package ru.alexeybuchnev.football.model

data class Team(
    val id: Int,
    val name: String,
    val founded: Int,
    val logoUrl: String,
    val venue: Venue? = null,
    val players: List<Player>
)
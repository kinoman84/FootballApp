package ru.alexeybuchnev.football.domain.entity

data class Team(
    val id: Int,
    val name: String,
    val founded: Int? = null,
    val logoUrl: String,
    val venue: Venue? = null
)
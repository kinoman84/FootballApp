package ru.alexeybuchnev.football.domain.entity

data class Venue(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val capacity: Int,
    val imageUrl: String
)
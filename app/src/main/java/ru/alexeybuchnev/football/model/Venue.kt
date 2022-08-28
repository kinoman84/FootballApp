package ru.alexeybuchnev.football.model

data class Venue(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val capacity: Int,
    val imageUrl: String
)
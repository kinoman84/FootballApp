package ru.alexeybuchnev.football.domain.entity

data class Player(
    val id: Int,
    val name: String,
    val age: Int,
    val number: Int? = null,
    val position: String,
    val photoUrl: String
)
package ru.alexeybuchnev.football.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venue")
data class VenueEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val capacity: Int,
    val imageUrl: String,
    val teamId: Int
)

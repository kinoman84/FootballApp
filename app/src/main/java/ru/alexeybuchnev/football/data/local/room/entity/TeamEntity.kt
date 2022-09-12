package ru.alexeybuchnev.football.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val founded: Int? = null,
    val logoUrl: String
)
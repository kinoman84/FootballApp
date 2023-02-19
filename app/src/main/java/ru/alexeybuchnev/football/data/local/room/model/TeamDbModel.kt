package ru.alexeybuchnev.football.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val founded: Int? = null,
    val logoUrl: String
)
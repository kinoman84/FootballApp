package ru.alexeybuchnev.football.data.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "teams")
data class TeamWithVenueDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val founded: Int? = null,
    val logoUrl: String,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val venueDbModel: VenueDbModel
)

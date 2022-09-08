package ru.alexeybuchnev.football.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alexeybuchnev.football.data.local.room.dao.TeamDao
import ru.alexeybuchnev.football.data.local.room.dao.VenueDao
import ru.alexeybuchnev.football.data.local.room.entity.TeamEntity
import ru.alexeybuchnev.football.data.local.room.entity.VenueEntity

@Database(entities = [TeamEntity::class, VenueEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao() : TeamDao
    abstract fun venueDao() : VenueDao
}
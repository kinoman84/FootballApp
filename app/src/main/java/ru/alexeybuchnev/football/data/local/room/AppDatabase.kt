package ru.alexeybuchnev.football.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alexeybuchnev.football.data.local.room.dao.TeamDao
import ru.alexeybuchnev.football.data.local.room.dao.VenueDao
import ru.alexeybuchnev.football.data.local.room.model.TeamDbModel
import ru.alexeybuchnev.football.data.local.room.model.VenueDbModel

@Database(entities = [TeamDbModel::class, VenueDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao() : TeamDao
    abstract fun venueDao() : VenueDao
}
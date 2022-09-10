package ru.alexeybuchnev.football.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.alexeybuchnev.football.data.local.room.entity.VenueEntity

@Dao
interface VenueDao {

    @Query("SELECT * FROM venue")
    suspend fun getVenue(): List<VenueEntity>

    @Query("SELECT * FROM venue WHERE teamId = :teamId")
    suspend fun getVenue(teamId: Int): VenueEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenue(venue: List<VenueEntity>)
}
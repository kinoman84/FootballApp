package ru.alexeybuchnev.football.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.alexeybuchnev.football.data.local.room.model.VenueDbModel

@Dao
interface VenueDao {

    @Query("SELECT * FROM venue WHERE teamId = :teamId")
    fun getVenue(teamId: Int): LiveData<VenueDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenue(venue: List<VenueDbModel>)
}
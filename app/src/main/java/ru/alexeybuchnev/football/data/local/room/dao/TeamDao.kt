package ru.alexeybuchnev.football.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.alexeybuchnev.football.data.local.room.model.TeamDbModel
import ru.alexeybuchnev.football.data.local.room.model.TeamWithVenueDbModel

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    fun getTeams(): LiveData<List<TeamDbModel>>

    @Query("SELECT * FROM teams WHERE id = :teamId")
    fun getTeam(teamId: Int): LiveData<TeamWithVenueDbModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamDbModel>)
}
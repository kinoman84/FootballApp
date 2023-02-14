package ru.alexeybuchnev.football.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.alexeybuchnev.football.data.local.room.model.TeamDbModel

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    suspend fun getTeams(): List<TeamDbModel>

    @Query("SELECT * FROM teams WHERE id = :teamId")
    suspend fun getTeam(teamId: Int): TeamDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamDbModel>)
}
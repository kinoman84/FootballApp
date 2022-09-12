package ru.alexeybuchnev.football.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.alexeybuchnev.football.data.local.room.entity.TeamEntity

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    suspend fun getTeams(): List<TeamEntity>

    @Query("SELECT * FROM teams WHERE id = :teamId")
    suspend fun getTeam(teamId: Int): TeamEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamEntity>)
}
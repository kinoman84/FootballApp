package ru.alexeybuchnev.football.data.local

import androidx.lifecycle.LiveData
import ru.alexeybuchnev.football.data.local.room.model.TeamDbModel
import ru.alexeybuchnev.football.data.local.room.model.TeamWithVenueDbModel
import ru.alexeybuchnev.football.data.local.room.model.VenueDbModel

interface LocalDataSource {
    fun getTeams() : LiveData<List<TeamDbModel>>
    fun getTeam(teamId: Int) : LiveData<TeamWithVenueDbModel>?
    suspend fun saveTeams(teams: List<TeamDbModel>)
    fun getVenue(teamId: Int): LiveData<VenueDbModel>
    suspend fun saveVenues(venue: List<VenueDbModel>)
}
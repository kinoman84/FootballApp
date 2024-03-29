package ru.alexeybuchnev.football.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import ru.alexeybuchnev.football.data.local.room.AppDatabase
import ru.alexeybuchnev.football.data.local.room.model.TeamDbModel
import ru.alexeybuchnev.football.data.local.room.model.TeamWithVenueDbModel
import ru.alexeybuchnev.football.data.local.room.model.VenueDbModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val db: AppDatabase
) : LocalDataSource {

    override fun getTeams(): LiveData<List<TeamDbModel>> =
        db.teamDao().getTeams()

    override fun getTeam(teamId: Int): LiveData<TeamWithVenueDbModel>? =
        db.teamDao().getTeam(teamId)

    override suspend fun saveTeams(teams: List<TeamDbModel>) {
        db.teamDao().insertTeams(teams)
    }

    override fun getVenue(teamId: Int): LiveData<VenueDbModel> =
        db.venueDao().getVenue(teamId)

    override suspend fun saveVenues(venue: List<VenueDbModel>) {
        db.venueDao().insertVenue(venue)
    }
}
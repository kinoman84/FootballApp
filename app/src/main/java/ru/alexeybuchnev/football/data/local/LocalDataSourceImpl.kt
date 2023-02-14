package ru.alexeybuchnev.football.data.local

import android.content.Context
import androidx.room.Room
import ru.alexeybuchnev.football.data.local.room.AppDatabase
import ru.alexeybuchnev.football.data.local.room.model.TeamDbModel
import ru.alexeybuchnev.football.data.local.room.model.VenueDbModel
import ru.alexeybuchnev.football.domain.entity.Team
import ru.alexeybuchnev.football.domain.entity.Venue
import java.lang.IllegalArgumentException

class LocalDataSourceImpl(applicationContext: Context) : LocalDataSource {

    private val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "team-database"
    ).build()

    override suspend fun getTeams(): List<Team> {
        val teamsEntity = db.teamDao().getTeams()
        val venuesEntity = db.venueDao().getVenue()

        return teamsEntity.map {
            Team(
                id = it.id,
                name = it.name,
                founded = it.founded,
                logoUrl = it.logoUrl,
                venue = toVenue(venuesEntity.find { venueEntity -> venueEntity.teamId == it.id })
            )
        }
    }

    private fun toVenue (venueDbModel: VenueDbModel?): Venue? {

        return if (venueDbModel == null) {
            null
        } else {
            Venue(
                id = venueDbModel.id,
                name = venueDbModel.name,
                address = venueDbModel.address,
                city = venueDbModel.city,
                capacity = venueDbModel.capacity,
                imageUrl = venueDbModel.imageUrl
            )
        }
    }

    override suspend fun getTeam(teamId: Int): Team? {
        val team = db.teamDao().getTeam(teamId)
        val venue = db.venueDao().getVenue(teamId)

        return if (team == null) {
            null
        } else {
            Team(
                id = team.id,
                name = team.name,
                founded = team.founded,
                logoUrl = team.logoUrl,
                venue = toVenue(venue)
            )
        }
    }

    override suspend fun saveTeams(teams: List<Team>) {

        val teamsEntity: List<TeamDbModel> = teams.map {
            TeamDbModel(
                id = it.id,
                name = it.name,
                founded = it.founded,
                logoUrl = it.logoUrl
            )
        }

        db.teamDao().insertTeams(teamsEntity)

        val venuesEntity: List<VenueDbModel> = teams.map { team ->
            team.venue?.let {
                VenueDbModel(
                    id = it.id,
                    name = it.name,
                    address = it.address,
                    city = it.city,
                    capacity = it.capacity,
                    imageUrl = it.imageUrl,
                    teamId = team.id
                )
            } ?: return
        }

        db.venueDao().insertVenue(venuesEntity)
    }

    companion object {
        private var instance: LocalDataSource? = null

        fun initialize(applicationContext: Context) {
            if (instance == null) {
                instance = LocalDataSourceImpl(applicationContext)
            }
        }

        fun get(): LocalDataSource {
            return instance ?: throw IllegalArgumentException("LocalDataSourceImpl must be initialized")
        }
    }
}
package ru.alexeybuchnev.football.data.local

import android.content.Context
import androidx.room.Room
import ru.alexeybuchnev.football.data.local.room.AppDatabase
import ru.alexeybuchnev.football.data.local.room.entity.TeamEntity
import ru.alexeybuchnev.football.data.local.room.entity.VenueEntity
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

    private fun toVenue (venueEntity: VenueEntity?): Venue? {

        return if (venueEntity == null) {
            null
        } else {
            Venue(
                id = venueEntity.id,
                name = venueEntity.name,
                address = venueEntity.address,
                city = venueEntity.city,
                capacity = venueEntity.capacity,
                imageUrl = venueEntity.imageUrl
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

        val teamsEntity: List<TeamEntity> = teams.map {
            TeamEntity(
                id = it.id,
                name = it.name,
                founded = it.founded,
                logoUrl = it.logoUrl
            )
        }

        db.teamDao().insertTeams(teamsEntity)

        val venuesEntity: List<VenueEntity> = teams.map { team ->
            team.venue?.let {
                VenueEntity(
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
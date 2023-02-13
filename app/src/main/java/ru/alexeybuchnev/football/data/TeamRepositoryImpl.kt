package ru.alexeybuchnev.football.data

import ru.alexeybuchnev.football.data.local.LocalDataSource
import ru.alexeybuchnev.football.data.local.LocalDataSourceImpl
import ru.alexeybuchnev.football.data.network.NetworkDataSource
import ru.alexeybuchnev.football.data.network.retrofit.RetrofitDataSource
import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.domain.entity.Team
import ru.alexeybuchnev.football.domain.repository.TeamRepository
import java.lang.IllegalArgumentException

class TeamRepositoryImpl private constructor() : TeamRepository {

    private val remoteDataSource: NetworkDataSource  = RetrofitDataSource()
    private val localDataSource: LocalDataSource = LocalDataSourceImpl.get()

    override suspend fun getTeams(): List<Team> {

        val teams = remoteDataSource.getTeams()
        localDataSource.saveTeams(teams)

        return teams
    }

    override suspend fun getTeamsCash(): List<Team> {
        return localDataSource.getTeams()
    }

    override suspend fun getTeam(teamId: Int): Team {
        return localDataSource.getTeam(teamId) ?: remoteDataSource.getTeam(teamId)
    }

    override suspend fun getPlayers(teamId: Int): List<Player> {
        return remoteDataSource.getPlayers(teamId)
    }

    companion object {
        private var instance: TeamRepository? = null

        fun initialize() {
            if (instance == null) {
                instance = TeamRepositoryImpl()
            }
        }

        fun get() : TeamRepository {
            return instance ?: throw IllegalArgumentException("TeamRepository must be initialized")
        }
    }
}
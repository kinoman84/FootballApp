package ru.alexeybuchnev.football.data

import ru.alexeybuchnev.football.data.local.LocalDataSource
import ru.alexeybuchnev.football.data.local.LocalDataSourceImpl
import ru.alexeybuchnev.football.data.network.NetworkDataSource
import ru.alexeybuchnev.football.data.network.retrofit.RetrofitDataSource
import ru.alexeybuchnev.football.model.Player
import ru.alexeybuchnev.football.model.Team
import java.lang.IllegalArgumentException

class TeamRepositoryImpl private constructor() : TeamRepository {

    private val remoteData: NetworkDataSource  = RetrofitDataSource()
    private val localRepository: LocalDataSource = LocalDataSourceImpl.get()

    override suspend fun getTeams(): List<Team> {

        val teams = remoteData.getTeams()
        localRepository.saveTeams(teams)

        return teams
    }

    override suspend fun getTeamsCash(): List<Team> {
        return localRepository.getTeams()
    }

    override suspend fun getTeam(teamId: Int): Team {
        return remoteData.getTeam(teamId)
    }

    override suspend fun getPlayers(teamId: Int): List<Player> {
        return remoteData.getPlayers(teamId)
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
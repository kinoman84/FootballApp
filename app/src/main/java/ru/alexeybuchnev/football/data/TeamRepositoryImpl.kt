package ru.alexeybuchnev.football.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.alexeybuchnev.football.data.local.LocalDataSource
import ru.alexeybuchnev.football.data.local.LocalDataSourceImpl
import ru.alexeybuchnev.football.data.remote.RemoteDataSource
import ru.alexeybuchnev.football.data.remote.retrofit.RetrofitDataSourceImpl
import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.domain.entity.Team
import ru.alexeybuchnev.football.domain.repository.TeamRepository
import java.lang.IllegalArgumentException

class TeamRepositoryImpl private constructor() : TeamRepository {

    private val remoteDataSource: RemoteDataSource = RetrofitDataSourceImpl()
    private val localDataSource: LocalDataSource = LocalDataSourceImpl.get()

    override fun getTeams(): LiveData<List<Team>> {
        return Transformations.map(localDataSource.getTeams()) { listTeamDbModel ->
            listTeamDbModel.map {
                TeamMapper.teamDbModelToTeam(
                    it
                )
            }
        }
    }

    override fun getTeam(teamId: Int): LiveData<Team> {

        val team = localDataSource.getTeam(teamId) ?: throw RuntimeException(
            "don't find team by id $teamId"
        )

        return Transformations.map(team) {
            TeamMapper.teamWithVenueDbModelToTeam(it)
        }
    }

    override suspend fun getPlayers(teamId: Int): List<Player> {
        return TeamMapper.playerDtoListToPlayerList(remoteDataSource.getPlayers(teamId))
    }

    override suspend fun updateData() {
        val teamsDto = remoteDataSource.getTeams()
        localDataSource.saveTeams(
            teamsDto.map {
                TeamMapper.teamItemDtoToTeamDbModel(it)
            }
        )
        localDataSource.saveVenues(
            teamsDto.map {
                TeamMapper.teamItemDtoToVenueDbModel(it)
            }
        )
    }

    companion object {
        private var instance: TeamRepository? = null

        fun initialize() {
            if (instance == null) {
                instance = TeamRepositoryImpl()
            }
        }

        fun get(): TeamRepository {
            return instance ?: throw IllegalArgumentException("TeamRepository must be initialized")
        }
    }
}
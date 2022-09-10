package ru.alexeybuchnev.football.data

import ru.alexeybuchnev.football.model.Player
import ru.alexeybuchnev.football.model.Team
import ru.alexeybuchnev.football.model.Venue
import java.lang.IllegalArgumentException

class TeamRepositoryMockupImpl : TeamRepository {

    private val teamMap = mapOf<Int, Team>(
        555 to Team(
            id = 555,
            name = "CSKA Moscow",
            founded = 1911,
            logoUrl = "https://media.api-sports.io/football/teams/555.png",
            venue = Venue(
                id = 1333,
                name = "VEB Arena",
                address = "3-ya Peschanaya ul.",
                city = "Moskva",
                capacity = 30000,
                imageUrl = "https://media.api-sports.io/football/venues/1333.png"
            ),
            players = listOf(
                Player(
                    id = 464,
                    name = "Guilherme",
                    age = 37,
                    number = 1,
                    position = "Goalkeeper",
                    photoUrl = "https://media.api-sports.io/football/players/464.png"
                ),
                Player(
                    id = 476,
                    name = "D. Barinov",
                    age = 26,
                    number = 6,
                    position = "Midfielder",
                    photoUrl = "https://media.api-sports.io/football/players/476.png"
                ),
                Player(
                    id = 485,
                    name = "A. Miranchuk",
                    age = 27,
                    number = 11,
                    position = "Midfielder",
                    photoUrl = "https://media.api-sports.io/football/players/485.png"
                )
            )
        ),
        558 to Team(
            id = 558,
            name = "Spartak Moscow",
            founded = 1922,
            logoUrl = "https://media.api-sports.io/football/teams/558.png",
            venue = Venue(
                id = 18673,
                name = "Otkritie Bank Arena",
                address = "Volokolamskoye shosse, Tushino",
                city = "Moskva",
                capacity = 45360,
                imageUrl = "https://media.api-sports.io/football/venues/18673.png"
            ),
            players = listOf(
                Player(
                    id = 464,
                    name = "Guilherme",
                    age = 37,
                    number = 1,
                    position = "Goalkeeper",
                    photoUrl = "https://media.api-sports.io/football/players/464.png"
                ),
                Player(
                    id = 476,
                    name = "D. Barinov",
                    age = 26,
                    number = 6,
                    position = "Midfielder",
                    photoUrl = "https://media.api-sports.io/football/players/476.png"
                ),
                Player(
                    id = 485,
                    name = "A. Miranchuk",
                    age = 27,
                    number = 11,
                    position = "Midfielder",
                    photoUrl = "https://media.api-sports.io/football/players/485.png"
                )
            )
        ),
        597 to Team(
            id = 597,
            name = "Lokomotiv Moscow",
            founded = 1923,
            logoUrl = "https://media.api-sports.io/football/teams/597.png",
            venue = Venue(
                id = 1327,
                name = "RZD Arena",
                address = "ul. Bol&apos;shaya Cherkizovskaya 125",
                city = "Moskva",
                capacity = 28800,
                imageUrl = "https://media.api-sports.io/football/venues/1327.png"
            ),
            players = listOf(
                Player(
                    id = 464,
                    name = "Guilherme",
                    age = 37,
                    number = 1,
                    position = "Goalkeeper",
                    photoUrl = "https://media.api-sports.io/football/players/464.png"
                ),
                Player(
                    id = 476,
                    name = "D. Barinov",
                    age = 26,
                    number = 6,
                    position = "Midfielder",
                    photoUrl = "https://media.api-sports.io/football/players/476.png"
                ),
                Player(
                    id = 485,
                    name = "A. Miranchuk",
                    age = 27,
                    number = 11,
                    position = "Midfielder",
                    photoUrl = "https://media.api-sports.io/football/players/485.png"
                )
            )
        )
    )

    override suspend fun getTeams(): List<Team> {
        return teamMap.map { it.value }
    }

    override suspend fun getTeam(teamId: Int): Team {
        return teamMap.getValue(teamId)
    }

    override suspend fun getPlayers(teamId: Int): List<Player> {
        var players = listOf<Player>()
        teamMap.get(teamId)?.let {
            players = it.players
        }
        return players
    }

    override suspend fun getTeamsCash(): List<Team> {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: TeamRepository? = null

        fun initialize() {
            if (instance == null) {
                instance = TeamRepositoryMockupImpl()
            }
        }

        fun get() : TeamRepository {
            return instance ?: throw IllegalArgumentException("TeamRepository must be initialized")
        }
    }
}
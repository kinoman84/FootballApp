package ru.alexeybuchnev.football.domain.usecase

import ru.alexeybuchnev.football.domain.repository.TeamRepository

class GetTeamListUseCase(private val repository: TeamRepository) {

    operator fun invoke() = repository.getTeams()

}
package ru.alexeybuchnev.football.domain.usecase

import ru.alexeybuchnev.football.domain.repository.TeamRepository

class GetTeamDetailsUseCase(private val repository: TeamRepository) {
    operator fun invoke(id: Int) = repository.getTeam(id)
}
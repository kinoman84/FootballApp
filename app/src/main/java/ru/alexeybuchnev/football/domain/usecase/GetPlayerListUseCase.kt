package ru.alexeybuchnev.football.domain.usecase

import ru.alexeybuchnev.football.domain.repository.TeamRepository

class GetPlayerListUseCase(private val repository: TeamRepository) {
    suspend operator fun invoke(teamId: Int) = repository.getPlayers(teamId)
}
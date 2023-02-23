package ru.alexeybuchnev.football.domain.usecase

import ru.alexeybuchnev.football.domain.repository.TeamRepository
import javax.inject.Inject

class GetTeamListUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    operator fun invoke() = repository.getTeams()
}
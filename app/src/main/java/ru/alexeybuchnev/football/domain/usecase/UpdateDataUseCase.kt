package ru.alexeybuchnev.football.domain.usecase

import ru.alexeybuchnev.football.domain.repository.TeamRepository
import javax.inject.Inject

class UpdateDataUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke() {
        repository.updateData()
    }
}
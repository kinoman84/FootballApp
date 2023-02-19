package ru.alexeybuchnev.football.domain.usecase

import ru.alexeybuchnev.football.domain.repository.TeamRepository

class UpdateDataUseCase(val repository: TeamRepository) {
    suspend operator fun invoke() {
        repository.updateData()
    }
}
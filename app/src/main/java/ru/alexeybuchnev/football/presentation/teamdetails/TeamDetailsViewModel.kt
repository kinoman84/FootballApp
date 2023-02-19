package ru.alexeybuchnev.football.presentation.teamdetails

import androidx.lifecycle.ViewModel
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.domain.usecase.GetTeamDetailsUseCase

class TeamDetailsViewModel : ViewModel() {

    private val getTeamDetailsUseCase = GetTeamDetailsUseCase(TeamRepositoryImpl.get())

    fun getTeamDetails(teamId: Int) = getTeamDetailsUseCase(teamId)
}
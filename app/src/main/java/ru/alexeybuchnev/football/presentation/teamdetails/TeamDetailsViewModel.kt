package ru.alexeybuchnev.football.presentation.teamdetails

import androidx.lifecycle.ViewModel
import ru.alexeybuchnev.football.domain.usecase.GetTeamDetailsUseCase
import javax.inject.Inject

class TeamDetailsViewModel @Inject constructor(
    val getTeamDetailsUseCase: GetTeamDetailsUseCase
) : ViewModel() {

    fun getTeamDetails(teamId: Int) = getTeamDetailsUseCase(teamId)
}
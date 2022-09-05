package ru.alexeybuchnev.football.presentation.teamdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.model.Team

class TeamDetailsViewModel : ViewModel() {

    private val teamRepository = TeamRepositoryImpl.get()

    private val mutableTeamDetailsLiveData = MutableLiveData<Team>()
    val teamDetailsLiveData : LiveData<Team>  get() = mutableTeamDetailsLiveData

    fun loadTeamDetails(teamId: Int) {

        viewModelScope.launch {
            val team = teamRepository.getTeam(teamId)

            mutableTeamDetailsLiveData.value = team
        }
    }
}
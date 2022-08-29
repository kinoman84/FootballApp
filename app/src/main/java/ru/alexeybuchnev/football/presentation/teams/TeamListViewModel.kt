package ru.alexeybuchnev.football.presentation.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.model.Team

class TeamListViewModel : ViewModel() {
    private val teamRepository = TeamRepositoryImpl.get()

    private val mutableTeamsLiveData = MutableLiveData<List<Team>>(emptyList())
    val teamLiveData: LiveData<List<Team>> get() = mutableTeamsLiveData

    fun loadTeams() {
        mutableTeamsLiveData.value = teamRepository.getTeams()
    }
}
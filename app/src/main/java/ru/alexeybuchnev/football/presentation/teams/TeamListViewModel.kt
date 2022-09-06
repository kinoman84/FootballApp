package ru.alexeybuchnev.football.presentation.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.model.Team
import java.lang.Exception

class TeamListViewModel : ViewModel() {

    private val teamRepository = TeamRepositoryImpl.get()

    private val mutableTeamsViewStateLiveData = MutableLiveData<TeamsListViewState>()
    val teamsViewStateLiveData: LiveData<TeamsListViewState> get() = mutableTeamsViewStateLiveData

    fun loadTeams() {

        mutableTeamsViewStateLiveData.value = TeamsListViewState.TeamsLoading

        viewModelScope.launch {
            val teams = teamRepository.getTeams()

            mutableTeamsViewStateLiveData.value = TeamsListViewState.TeamsLoaded(teams)
        }
    }

    sealed class TeamsListViewState {
        data class TeamsLoaded(val teams: List<Team>) :TeamsListViewState()
        data class Error(val exception: Exception) :TeamsListViewState()
        object TeamsLoading : TeamsListViewState()
    }
}
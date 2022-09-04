package ru.alexeybuchnev.football.presentation.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.data.TeamRepositoryMockupImpl
import ru.alexeybuchnev.football.data.network.retrofit.RetrofitDataSource
import ru.alexeybuchnev.football.model.Team

class TeamListViewModel : ViewModel() {
    //private val teamRepository = TeamRepositoryMockupImpl.get()
    private val teamRepository = TeamRepositoryImpl()

    private val mutableTeamsLiveData = MutableLiveData<List<Team>>(emptyList())
    val teamLiveData: LiveData<List<Team>> get() = mutableTeamsLiveData

    fun loadTeams() {
        //mutableTeamsLiveData.value = teamRepository.getTeams()
        viewModelScope.launch {
            val teams = teamRepository.getTeams()

            mutableTeamsLiveData.value = teams
        }
    }
}
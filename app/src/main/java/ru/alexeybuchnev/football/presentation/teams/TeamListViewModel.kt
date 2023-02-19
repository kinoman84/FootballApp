package ru.alexeybuchnev.football.presentation.teams

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.domain.entity.Team
import ru.alexeybuchnev.football.domain.usecase.GetTeamListUseCase
import ru.alexeybuchnev.football.domain.usecase.UpdateDataUseCase

class TeamListViewModel : ViewModel() {

    private val teamRepository = TeamRepositoryImpl.get()
    private val getTeamListUseCase = GetTeamListUseCase(teamRepository)
    private val updateDataUseCase = UpdateDataUseCase(teamRepository)

    private val teamListLiveData = Transformations.map(getTeamListUseCase()) {
        if (it.isNullOrEmpty()) {
            TeamsListViewState.TeamsLoading
        }
        TeamsListViewState.TeamsLoaded(it)
    }

    private val exceptionHandler = CoroutineExceptionHandler {
        _, throwable ->
        mutableLoadingStateLiveData.value = TeamsListViewState.Error(throwable)
    }

    private val mutableLoadingStateLiveData = MutableLiveData<TeamsListViewState>()
    private val loadingStateLiveData: LiveData<TeamsListViewState> get() = mutableLoadingStateLiveData

    val teamsViewStateLiveData = MediatorLiveData<TeamsListViewState>()

    init {
        teamsViewStateLiveData.addSource(loadingStateLiveData) {teamsViewStateLiveData.value = it}
        teamsViewStateLiveData.addSource(teamListLiveData) {teamsViewStateLiveData.value = it}
    }


    fun refreshData() {
        mutableLoadingStateLiveData.value = TeamsListViewState.TeamsLoading
        viewModelScope.launch(exceptionHandler) {
            updateDataUseCase()
        }
    }

    sealed class TeamsListViewState {
        data class TeamsLoaded(val teams: List<Team>) :TeamsListViewState()
        data class Error(val exception: Throwable) :TeamsListViewState()
        object TeamsLoading : TeamsListViewState()
    }
}
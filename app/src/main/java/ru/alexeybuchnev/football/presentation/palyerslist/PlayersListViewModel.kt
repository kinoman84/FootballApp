package ru.alexeybuchnev.football.presentation.palyerslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.model.Player
import ru.alexeybuchnev.football.model.Team
import java.lang.Exception

class PlayersListViewModel : ViewModel() {

    private val teamRepository = TeamRepositoryImpl.get()

    private val mutablePlayersStateListViewData = MutableLiveData<PlayersListViewState>()
    val playersStateListLiveData: LiveData<PlayersListViewState> get() = mutablePlayersStateListViewData

    fun loadPlayers(teamId: Int) {
        viewModelScope.launch {
            mutablePlayersStateListViewData.value = PlayersListViewState.PlayersLoading
            val players = teamRepository.getPlayers(teamId)
            mutablePlayersStateListViewData.value = PlayersListViewState.PlayersLoaded(players)
        }
    }

    sealed class PlayersListViewState {
        data class PlayersLoaded(val players: List<Player>) :PlayersListViewState()
        data class Error(val exception: Exception) :PlayersListViewState()
        object PlayersLoading : PlayersListViewState()
    }
}
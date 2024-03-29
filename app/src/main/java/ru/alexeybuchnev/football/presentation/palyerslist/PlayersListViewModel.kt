package ru.alexeybuchnev.football.presentation.palyerslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.alexeybuchnev.football.domain.entity.Player
import ru.alexeybuchnev.football.domain.usecase.GetPlayerListUseCase
import javax.inject.Inject

class PlayersListViewModel @Inject constructor(
    private val getPlayerListUseCase: GetPlayerListUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler {
            _, throwable ->
        mutablePlayersStateListViewData.value = PlayersListViewState.Error(throwable)
    }

    private val mutablePlayersStateListViewData = MutableLiveData<PlayersListViewState>()
    val playersStateListLiveData: LiveData<PlayersListViewState> get() = mutablePlayersStateListViewData

    fun loadPlayers(teamId: Int) {
        viewModelScope.launch(exceptionHandler) {
            mutablePlayersStateListViewData.value = PlayersListViewState.PlayersLoading
            val players = getPlayerListUseCase(teamId)
            mutablePlayersStateListViewData.value = PlayersListViewState.PlayersLoaded(players)
        }
    }

    sealed class PlayersListViewState {
        data class PlayersLoaded(val players: List<Player>) :PlayersListViewState()
        data class Error(val exception: Throwable) :PlayersListViewState()
        object PlayersLoading : PlayersListViewState()
    }
}
package ru.alexeybuchnev.football.presentation.palyerslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.model.Player

class PlayersListViewModel : ViewModel() {

    private val teamRepository = TeamRepositoryImpl.get()

    private val mutablePlayersListViewData = MutableLiveData<List<Player>>(emptyList())
    val playersListLiveData: LiveData<List<Player>> get() = mutablePlayersListViewData

    fun loadPlayers(teamId: Int) {
        viewModelScope.launch {
            val players = teamRepository.getPlayers(teamId)
            mutablePlayersListViewData.value = players
        }
    }
}
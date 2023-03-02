package ru.alexeybuchnev.football.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import ru.alexeybuchnev.football.presentation.palyerslist.PlayersListViewModel
import ru.alexeybuchnev.football.presentation.teamdetails.TeamDetailsViewModel
import ru.alexeybuchnev.football.presentation.teams.TeamListViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("TeamListViewModel")
    @Binds
    fun bindTeamListViewModel(impl: TeamListViewModel): ViewModel

    @IntoMap
    @StringKey("TeamDetailsViewModel")
    @Binds
    fun bindTeamDetailsViewModel(impl: TeamDetailsViewModel): ViewModel

    @IntoMap
    @StringKey("PlayersListViewModel")
    @Binds
    fun bindPlayersListViewModel(impl: PlayersListViewModel): ViewModel
}
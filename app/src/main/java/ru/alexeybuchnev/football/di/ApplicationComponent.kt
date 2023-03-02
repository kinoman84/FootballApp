package ru.alexeybuchnev.football.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.alexeybuchnev.football.presentation.palyerslist.PlayersListFragment
import ru.alexeybuchnev.football.presentation.teamdetails.TeamDetailsFragment
import ru.alexeybuchnev.football.presentation.teams.TeamListViewModel
import ru.alexeybuchnev.football.presentation.teams.TeamsListFragment

@ApplicationScope
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(teamListViewModel: TeamListViewModel)
    fun inject(teamsListFragment: TeamsListFragment)
    fun inject(teamDetailsFragment: TeamDetailsFragment)
    fun inject(playersListFragment: PlayersListFragment)

    //собственная реализация билдера. позволяет засунуть в граф зависимостей нужные объкты
    @Component.Builder
    interface ApplicationComponentBuilder {

        @BindsInstance
        fun context(context: Context): ApplicationComponentBuilder

        fun build(): ApplicationComponent
    }
}
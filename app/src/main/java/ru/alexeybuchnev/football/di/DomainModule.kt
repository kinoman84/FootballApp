package ru.alexeybuchnev.football.di

import dagger.Binds
import dagger.Module
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.domain.repository.TeamRepository

@Module
interface DomainModule {

    //связываем реализацию с интерфейсом
    @ApplicationScope
    @Binds
    fun bindRepository(impl: TeamRepositoryImpl): TeamRepository
}
package ru.alexeybuchnev.football.di

import android.app.Application
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.data.TeamRepositoryMockupImpl

class FootballApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TeamRepositoryMockupImpl.initialize()
        TeamRepositoryImpl.initialize()
    }
}
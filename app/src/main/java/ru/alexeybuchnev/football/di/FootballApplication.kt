package ru.alexeybuchnev.football.di

import android.app.Application
import ru.alexeybuchnev.football.data.TeamRepositoryImpl
import ru.alexeybuchnev.football.data.local.LocalDataSourceImpl

class FootballApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalDataSourceImpl.initialize(this)
        TeamRepositoryImpl.initialize()
    }
}
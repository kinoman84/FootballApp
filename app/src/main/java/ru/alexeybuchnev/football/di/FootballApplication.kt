package ru.alexeybuchnev.football.di

import android.app.Application
import ru.alexeybuchnev.football.data.TeamRepositoryImpl

class FootballApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TeamRepositoryImpl.initialize()
    }
}
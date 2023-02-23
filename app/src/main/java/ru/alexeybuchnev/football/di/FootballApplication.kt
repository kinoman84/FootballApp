package ru.alexeybuchnev.football.di

import android.app.Application
import ru.alexeybuchnev.football.data.local.LocalDataSourceImpl

class FootballApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        LocalDataSourceImpl.initialize(this)
    }
}
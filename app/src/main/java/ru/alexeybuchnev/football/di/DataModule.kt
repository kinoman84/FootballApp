package ru.alexeybuchnev.football.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.alexeybuchnev.football.data.local.LocalDataSource
import ru.alexeybuchnev.football.data.local.LocalDataSourceImpl
import ru.alexeybuchnev.football.data.local.room.AppDatabase
import ru.alexeybuchnev.football.data.remote.RemoteDataSource
import ru.alexeybuchnev.football.data.remote.retrofit.RetrofitDataSourceImpl

@Module
interface DataModule {

    //Связь интерфейса и реализации
    @ApplicationScope
    @Binds
    fun provideLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource

    companion object {
        @ApplicationScope
        @Provides
        fun provideDataBase(applicationContext: Context): AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "team-database"
        ).build()

        //Так можно подложить реализацию класса
        @ApplicationScope
        @Provides
        fun provideRemoteDataSource(): RemoteDataSource {
            return RetrofitDataSourceImpl()
        }
    }
}
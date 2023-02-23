package ru.alexeybuchnev.football.di

import dagger.Module
import dagger.Provides
import ru.alexeybuchnev.football.data.local.LocalDataSource
import ru.alexeybuchnev.football.data.local.LocalDataSourceImpl
import ru.alexeybuchnev.football.data.remote.RemoteDataSource
import ru.alexeybuchnev.football.data.remote.retrofit.RetrofitDataSourceImpl

@Module
class DataModule {

    //Так можно подложить реализацию класса
    @ApplicationScope
    @Provides
    fun provideRemoteDataSource() : RemoteDataSource {
        return RetrofitDataSourceImpl()
    }

    @ApplicationScope
    @Provides
    fun provideLocalDataSource() : LocalDataSource {
        return LocalDataSourceImpl.get()
    }
}
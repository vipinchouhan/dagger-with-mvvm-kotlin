package com.myuidemo.di.module

import com.myuidemo.data.source.remote.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Provides
    @Singleton
    fun getTeamDetailRemote(): TeamRemoteDataSource {
        return TeamRemoteDataSource
    }



}
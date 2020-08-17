package com.myuidemo.di.module

import com.myuidemo.data.source.*
import com.myuidemo.data.source.remote.*
import com.konstant.kotlin.api.ApiService
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun ProvideTeamDetialRepository(
        @Named("service_1") apiService: ApiService,
        executor: Executor,
        remoteDataSource: TeamRemoteDataSource
    ): TeamRepository {
        return TeamRepository(
            apiService,
            remoteDataSource,
            executor
        )
    }



}
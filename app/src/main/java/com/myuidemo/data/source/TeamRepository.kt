package com.myuidemo.data.source

import android.arch.lifecycle.MediatorLiveData

import com.myuidemo.api.response.Resource
import com.myuidemo.api.response.Status

import com.myuidemo.data.source.remote.TeamRemoteDataSource
import com.konstant.kotlin.api.ApiService
import com.myuidemo.api.response.TeamResponse
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamRepository {
    var apiService: ApiService
    var executor: Executor
    var remoteDataSource: TeamRemoteDataSource

    @Inject
    constructor(
        apiService: ApiService,
        remoteDataSource: TeamRemoteDataSource,
        executor: Executor
    ) {
        this.apiService = apiService
        this.executor = executor
        this.remoteDataSource = remoteDataSource
    }



    fun getTeamDetail(): MediatorLiveData<Resource<TeamResponse>> {
        var liveData: MediatorLiveData<Resource<TeamResponse>> = MediatorLiveData()
        liveData.postValue(Resource(Status.LOADING, null, "Loading"))
        if (apiService == null) {
            liveData.postValue(Resource(Status.ERROR, null, "Error"))
            return liveData
        }

        liveData = remoteDataSource.getTeamDetail(apiService, executor)



        return liveData

    }

}
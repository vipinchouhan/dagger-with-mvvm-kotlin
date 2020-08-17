package com.myuidemo.myviewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel

import com.myuidemo.api.response.Resource
import com.myuidemo.api.response.Status
import com.myuidemo.data.source.TeamRepository
import com.myuidemo.api.response.TeamResponse
import javax.inject.Inject

class TeamViewModel() : ViewModel() {

    lateinit var userRepo: TeamRepository

    private var teamLiveData: MediatorLiveData<Resource<TeamResponse>>


    @Inject
    constructor(userRepo: TeamRepository) : this() {
        this.userRepo = userRepo
    }

    init {

        teamLiveData = MediatorLiveData()


        teamLiveData.value = Resource<TeamResponse>(Status.IDEL, null, "IDEL")

    }

    fun getTeamDetail() {
        if (userRepo == null) {
            teamLiveData.value = Resource<TeamResponse>(Status.ERROR, null, "Error")
            return
        }

        teamLiveData.addSource(
            userRepo.getTeamDetail(),
            Observer<Resource<TeamResponse>>() { t: Resource<TeamResponse>? ->
                teamLiveData.postValue(t)
            })
    }

    fun getTeamObserver(): MediatorLiveData<Resource<TeamResponse>> {
        return teamLiveData
    }




}
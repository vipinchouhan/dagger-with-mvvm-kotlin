package com.myuidemo.data.source.remote

import android.annotation.SuppressLint
import android.arch.lifecycle.MediatorLiveData

import com.myuidemo.api.response.*

import com.konstant.kotlin.api.ApiService
import com.myuidemo.api.response.TeamResponse
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

object TeamRemoteDataSource {



    @SuppressLint("CheckResult")
    fun getTeamDetail(
        mApiService: ApiService,
        executor: Executor
    ): MediatorLiveData<Resource<TeamResponse>> {
        var mediatorData: MediatorLiveData<Resource<TeamResponse>> = MediatorLiveData()

        mediatorData.postValue(Resource(Status.LOADING, null, "loading"))

        if (mApiService == null) {
            mediatorData.postValue(Resource(Status.ERROR, null, "error"))
            return mediatorData
        }

        executor.execute {
            mApiService.getTeamDetail()
                .enqueue(object : retrofit2.Callback<TeamResponse> {

                    override fun onResponse(
                        call: Call<TeamResponse>,
                        response: Response<TeamResponse>
                    ) {
                        if (response.code() == 200 && response.body()!=null) {
                            mediatorData.setValue(
                                Resource(
                                    Status.SUCCESS,
                                    response.body(),
                                    "success"
                                )
                            )
                        }
                    }

                    override fun onFailure(
                        call: Call<TeamResponse>,
                        t: Throwable
                    ) {
                        mediatorData.setValue(Resource(Status.ERROR, null, "Server Error"))
                    }
                })
        }

        return mediatorData
    }


}
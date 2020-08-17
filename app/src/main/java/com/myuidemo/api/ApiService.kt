package com.konstant.kotlin.api

import com.myuidemo.api.response.TeamResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("api.thefullshow.com/players.json")
    fun getTeamDetail(): Call<TeamResponse>
}
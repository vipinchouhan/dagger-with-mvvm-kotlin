package com.myuidemo.api.response

import android.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data class TeamResponse(

    @SerializedName("wicketkeeper")
    var wicketkeeper: ArrayList<PlayerModel>? = null,

    @SerializedName("batsman")
    var batsman: ArrayList<PlayerModel>? = null,

    @SerializedName("allrounder")
    var allrounder: ArrayList<PlayerModel>? = null,

    @SerializedName("bowler")
    var bowler: ArrayList<PlayerModel>? = null

): BaseObservable()
package com.myuidemo.api.response

import android.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

data class PlayerModel(
    @SerializedName("name") var name:String,
    @SerializedName("image") var image:String,
    @SerializedName("isCaptain") var isCaptain:Boolean,
    @SerializedName("isViceCaptain") var isViceCaptain:Boolean
):BaseObservable()



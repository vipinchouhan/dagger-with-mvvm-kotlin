package com.myuidemo.data.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.myuidemo.data.source.TeamRepository
import com.myuidemo.myviewmodel.TeamViewModel
import javax.inject.Inject

class TeamModelFactory() : ViewModelProvider.Factory  {

    lateinit var  respo : TeamRepository

    @Inject
    constructor(respo : TeamRepository): this() {
        this.respo = respo
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = TeamViewModel(respo) as T

}
package com.myuidemo.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.myuidemo.data.factory.TeamModelFactory
import com.myuidemo.di.key.ViewModelKey
import com.myuidemo.myviewmodel.TeamViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TeamViewModel::class)
    internal abstract fun bindTeamViewModel(repoViewModel: TeamViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: TeamModelFactory): ViewModelProvider.Factory

}

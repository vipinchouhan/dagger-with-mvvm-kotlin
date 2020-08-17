package com.myuidemo.di.module

import com.myuidemo.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeProductListActivity(): MainActivity?

}
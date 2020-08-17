package com.myuidemo

import android.app.Activity
import android.support.multidex.MultiDexApplication
import com.konstant.kotlin.api.ApiService
import com.myuidemo.di.component.DaggerAppComponent
import javax.inject.Inject
import dagger.android.*




class MyUiDemoApplication : MultiDexApplication(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
      DaggerAppComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
    }
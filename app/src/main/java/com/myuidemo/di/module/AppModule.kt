package com.myuidemo.di.module

import android.app.Application

import android.os.StrictMode
import com.myuidemo.BuildConfig

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.konstant.kotlin.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, RemoteDataModule::class, RepositoryModule::class])
class AppModule {

    companion object {
        val BASE_URL = "https://s3.ap-south-1.amazonaws.com/"

    }
    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Named("retrofit_Service_1")
    fun provideRetrofit(gson: Gson, app: Application): Retrofit {
        var connectionTimeout: Long = 0
        var readTimeout: Long = 0
        var writeTimeout: Long = 0
        var shouldRetryOnConnectionFailure = true

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        builder.connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.MINUTES)
            .writeTimeout(writeTimeout, TimeUnit.MINUTES)
            .retryOnConnectionFailure(shouldRetryOnConnectionFailure)



        if (BuildConfig.DEBUG)
            builder.addInterceptor(httpLoggingInterceptor)


        val okHttpClient = builder.build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @Provides
    @Singleton
    @Named("service_1")
    fun provideWebService(@Named("retrofit_Service_1") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }




}
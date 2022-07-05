package com.acious.plabs.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkClient(): OkHttpClient {
        val timeoutSecond = 10L
        return OkHttpClient.Builder()
            .readTimeout(timeoutSecond, TimeUnit.SECONDS)
            .build()
    }
}
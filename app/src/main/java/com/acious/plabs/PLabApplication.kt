package com.acious.plabs

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PLabApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
package com.example.dareWheel

import android.app.Application
import com.example.dareWheel.di.AppContainer
import com.example.dareWheel.di.AppDataContainer

class ChallengeApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
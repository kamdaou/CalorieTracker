package com.plcoding.calorytracker

import android.app.Application
import com.example.onboarding_domain.di.onBoardingDomainModule
import com.example.tracker_data.di.trackerDataModule
import com.example.tracker_domain.di.trackerDomainModule
import com.example.tracker_presentation.di.trackerPresentationModule
import com.plcoding.calorytracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, onBoardingDomainModule, trackerDataModule, trackerDomainModule, trackerPresentationModule)
        }
    }
}
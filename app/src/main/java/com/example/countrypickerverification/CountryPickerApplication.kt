package com.example.countrypickerverification

import android.app.Application
import com.example.countrypickerverification.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
class CountryPickerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CountryPickerApplication)
            modules(appModule)
        }

    }
}
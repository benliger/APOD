package com.benliger.nasaapod

import android.app.Application
import com.benliger.nasaapod.service.di.ApodModule
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ApodApplication : Application() {

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO here is a good idea to add logs to a crash utils such as Crashlytics
            // Timber.plant(LoggerCrashlytics())
        }

        startKoin {
            androidContext(this@ApodApplication)
            modules(ApodModule.apodServiceModule)
        }
        super.onCreate()

        registerRxUncaughtExceptionHandler()
    }

    private fun registerRxUncaughtExceptionHandler() {
        RxJavaPlugins.setErrorHandler { throwable ->
            Timber.e(throwable, "RxUncaughtException")
        }
    }
}
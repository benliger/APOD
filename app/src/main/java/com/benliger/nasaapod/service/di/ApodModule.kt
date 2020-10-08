package com.benliger.nasaapod.service.di

import com.benliger.nasaapod.BuildConfig
import com.benliger.nasaapod.service.api.NasaApodApiClient
import com.benliger.nasaapod.service.api.NasaApodApiClientImpl
import com.benliger.nasaapod.service.manager.ApodManager
import com.benliger.nasaapod.service.manager.ApodManagerImpl
import com.benliger.nasaapod.service.repository.ApodRepository
import com.benliger.nasaapod.service.repository.ApodRepositoryImpl
import com.benliger.nasaapod.ui.list.ListAstronomyPictureViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

object ApodModule {

    private const val BASE_URL: String = "https://api.nasa.gov"

    private val gson: Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    private val client: OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(59, TimeUnit.SECONDS)
            .readTimeout(59, TimeUnit.SECONDS)
            .dispatcher(Dispatcher().apply {
                maxRequests = 10
                maxRequestsPerHost = 10
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.BASIC
                }
            })
            .build()

    val apodServiceModule: Module = module {
        single<ApodManager> { ApodManagerImpl(get()) }
        single<ApodRepository> { ApodRepositoryImpl(get()) }
        single<NasaApodApiClient> {
            NasaApodApiClientImpl(
                BASE_URL,
                client,
                gson
            )
        }
        viewModel { ListAstronomyPictureViewModel(androidApplication(), get()) }
    }

}
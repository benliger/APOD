package com.benliger.nasaapod.service.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NasaApodApiClientImpl(
    baseUrl: String,
    client: OkHttpClient,
    gson: Gson,
) : NasaApodApiClient {

    private val api: NasaApodApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
        api = retrofit.create(NasaApodApi::class.java)
    }

    override fun getApod(key: String, date: String, hdImage: Boolean) =
        api.getApod(key, date, hdImage)

}
package com.benliger.nasaapod.service.api

class NasaApodApiClientImpl(private val api: NasaApodApi) : NasaApodApiClient {

    override fun getApod(key: String, date: String, hdImage: Boolean) =
        api.getApod(key, date, hdImage)

}
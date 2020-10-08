package com.benliger.nasaapod.service.api

import com.benliger.nasaapod.service.model.Apod
import io.reactivex.Single

interface NasaApodApiClient {

    /**
     * @param key api key use your own or default "DEMO_KEY"
     * @param date format is yyyy-MM-dd
     * @param hdImage HD or no HD that is the question
     */
    fun getApod(
        key: String = "DEMO_KEY",
        date: String,
        hdImage: Boolean = false
    ): Single<Apod>
}
package com.benliger.nasaapod.service.repository

import com.benliger.nasaapod.BuildConfig
import com.benliger.nasaapod.service.api.NasaApodApiClient
import com.benliger.nasaapod.service.model.Apod
import io.reactivex.Single
import org.joda.time.LocalDate

class ApodRepositoryImpl(private val apiClient: NasaApodApiClient) : ApodRepository {

    override fun getApod(date: LocalDate, highDefinition: Boolean): Single<Apod> {
        // It would be nice to save Apod into a database
        return apiClient.getApod(
            key = BuildConfig.NASA_API_KEY,
            date = date.toString(),
            hdImage = highDefinition
        )
    }


}
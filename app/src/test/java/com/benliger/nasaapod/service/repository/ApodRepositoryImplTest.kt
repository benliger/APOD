package com.benliger.nasaapod.service.repository

import com.benliger.nasaapod.BuildConfig
import com.benliger.nasaapod.service.api.NasaApodApiClient
import com.benliger.nasaapod.service.model.Apod
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.joda.time.LocalDate
import org.junit.Test

class ApodRepositoryImplTest {

    private val repository: ApodRepositoryImpl
    private val apiClient = mock<NasaApodApiClient>()

    init {
        repository = ApodRepositoryImpl(apiClient)
    }

    @Test
    fun getApod() {
        val date = "2020-10-08"
        val localDate = LocalDate.parse(date)
        val highDefinition = false
        val apod = Apod(date = date)
        val key = BuildConfig.NASA_API_KEY

        whenever(
            apiClient.getApod(
                key = key,
                date = date,
                hdImage = highDefinition
            )
        ).thenReturn(
            Single.just(apod)
        )

        repository.getApod(localDate, highDefinition)
            .test()
            .assertResult(apod)

        verify(apiClient).getApod(key, date, highDefinition)
        verifyNoMoreInteractions(apiClient)
    }
}
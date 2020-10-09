package com.benliger.nasaapod.service.api

import com.benliger.nasaapod.service.model.Apod
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class NasaApodApiClientImplTest {

    private val apiClient: NasaApodApiClientImpl
    private val api = mock<NasaApodApi>()

    init {
        apiClient = NasaApodApiClientImpl(api)
    }

    @Test
    fun getApod() {
        val key = "key"
        val date = "date"
        val hdImage = true
        val apod = Apod(date = date)

        whenever(api.getApod(key, date, hdImage)).thenReturn(Single.just(apod))

        apiClient.getApod(key, date, hdImage)
            .test()
            .assertNoErrors()
            .assertValue(apod)

        verify(api).getApod(key, date, hdImage)
        verifyNoMoreInteractions(api)
    }
}
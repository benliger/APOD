package com.benliger.nasaapod.service.manager

import com.benliger.nasaapod.service.model.Apod
import com.benliger.nasaapod.service.repository.ApodRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.joda.time.LocalDate
import org.junit.Test

class ApodManagerImplTest {

    private val manager: ApodManagerImpl
    private val repository = mock<ApodRepository>()

    init {
        manager = ApodManagerImpl(repository)
    }

    @Test
    fun getApod() {
        val date = "2020-10-08"
        val localDate = LocalDate.parse(date)
        val highDefinition = false
        val apod = Apod(date = date)

        whenever(repository.getApod(localDate, highDefinition)).thenReturn(Single.just(apod))

        manager.getApod(localDate, highDefinition)
            .test()
            .assertNoErrors()
            .assertResult(apod)

        verify(repository).getApod(localDate, highDefinition)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getLastApodList_askZeroDay() {
        manager.getLastApodList(numberOfDays = 0)
            .test()
            .assertNoErrors()
            .assertResult(emptyList())

        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getLastApodList_askNegativeAmountOfDays() {
        manager.getLastApodList(numberOfDays = -10)
            .test()
            .assertNoErrors()
            .assertResult(emptyList())

        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getLastApodList_askPositiveAmountOfDays() {
        val date = "2020-10-08"
        val startDate = LocalDate.parse(date)
        val highDefinition = false
        val apod1 = Apod(date = date)
        val apod2 = Apod(date = startDate.minusDays(1).toString())

        whenever(repository.getApod(startDate, highDefinition)).thenReturn(Single.just(apod1))
        whenever(repository.getApod(startDate.minusDays(1), highDefinition))
            .thenReturn(Single.just(apod2))

        manager.getLastApodList(numberOfDays = 2, startDate = startDate)
            .test()
            .assertNoErrors()
            .assertResult(listOf(apod1, apod2))

        verify(repository).getApod(startDate, highDefinition)
        verify(repository).getApod(startDate.minusDays(1), highDefinition)
        verifyNoMoreInteractions(repository)
    }
}
package com.benliger.nasaapod.service.manager

import com.benliger.nasaapod.service.model.Apod
import io.reactivex.Single
import org.joda.time.LocalDate

interface ApodManager {

    fun getApod(date: LocalDate, highDefinition: Boolean): Single<Apod>

    fun getLastApodList(
        numberOfDays: Int,
        startDate: LocalDate = LocalDate.now()
    ): Single<List<Apod>>

}
package com.benliger.nasaapod.service.repository

import com.benliger.nasaapod.service.model.Apod
import io.reactivex.Single
import org.joda.time.LocalDate

interface ApodRepository {

    fun getApod(date: LocalDate, highDefinition: Boolean): Single<Apod>
}

package com.benliger.nasaapod.util

import org.joda.time.LocalDate

data class DateRangeDesc(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
) :
    ClosedRange<LocalDate>, Iterable<LocalDate> {

    override fun iterator(): Iterator<LocalDate> {
        return DateIterator(this)
    }
}

class DateIterator(private val dateRange: DateRangeDesc) : Iterator<LocalDate> {

    private var current: LocalDate = dateRange.start

    override fun next(): LocalDate {
        val next = current
        current = current.minusDays(1)
        return next
    }

    override fun hasNext(): Boolean {
        return current >= dateRange.endInclusive
    }

}
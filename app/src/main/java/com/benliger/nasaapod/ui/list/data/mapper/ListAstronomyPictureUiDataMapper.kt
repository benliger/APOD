package com.benliger.nasaapod.ui.list.data.mapper

import com.benliger.nasaapod.service.model.Apod
import com.benliger.nasaapod.ui.list.data.AstronomyPictureRecyclerItem
import com.benliger.nasaapod.ui.list.data.PictureItem
import org.joda.time.LocalDate

class ListAstronomyPictureUiDataMapper {

    fun mapToUiData(listApod: List<Apod>): List<AstronomyPictureRecyclerItem> {
        val pictureListApod = listApod.filter { it.mediaType == APOD_IMAGE_MEDIA_TYPE }

        if (pictureListApod.isEmpty()) {
            return emptyList()
        }

        return pictureListApod.map {
            PictureItem(
                id = mapDateToLong(it.date),
                date = it.date,
                picture = it.url,
                name = it.title
            )
        }
    }

    private fun mapDateToLong(date: String) =
        LocalDate.parse(date).toDateTimeAtStartOfDay().millis

    companion object {
        const val APOD_IMAGE_MEDIA_TYPE: String = "image"
    }
}

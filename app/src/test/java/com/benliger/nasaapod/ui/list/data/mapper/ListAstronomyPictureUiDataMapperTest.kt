package com.benliger.nasaapod.ui.list.data.mapper

import com.benliger.nasaapod.service.model.Apod
import com.benliger.nasaapod.ui.list.data.PictureItem
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test


class ListAstronomyPictureUiDataMapperTest {

    private val mapper = ListAstronomyPictureUiDataMapper()


    @Test
    fun mapToUiData_emptyList_noRecyclerItem() {
        // GIVEN
        val listApod = emptyList<Apod>()
        // WHEN
        val result = mapper.mapToUiData(listApod)
        //THEN
        result.uiRecyclerItem.isEmpty().shouldBeEqualTo(true)
    }

    @Test
    fun mapToUiData_apodIsNotImage_noRecyclerItem() {
        // GIVEN
        val listApod = listOf(Apod(mediaType = "not an image"))
        // WHEN
        val result = mapper.mapToUiData(listApod)
        //THEN
        result.uiRecyclerItem.isEmpty().shouldBeEqualTo(true)
    }

    @Test
    fun mapToUiData_apodImage_PictureItem() {
        // GIVEN
        val listApod =
            listOf(Apod(mediaType = ListAstronomyPictureUiDataMapper.APOD_IMAGE_MEDIA_TYPE))
        // WHEN
        val result = mapper.mapToUiData(listApod)
        //THEN
        result.uiRecyclerItem.apply {
            isEmpty().shouldBeEqualTo(false)
            size.shouldBeEqualTo(1)
            this[0].apply {
                this.shouldBeInstanceOf(PictureItem::class.java)
                val item = this as PictureItem
                item.name.shouldBeEqualTo(listApod[0].title)
                item.picture.shouldBeEqualTo(listApod[0].url)
            }
        }
    }
}
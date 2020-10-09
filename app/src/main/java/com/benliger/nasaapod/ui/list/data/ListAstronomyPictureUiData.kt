package com.benliger.nasaapod.ui.list.data

import com.benliger.nasaapod.ui.common.RecyclerItem

data class ListAstronomyPictureUiData(val uiRecyclerItem: List<AstronomyPictureRecyclerItem> = emptyList())

sealed class AstronomyPictureRecyclerItem : RecyclerItem {
    enum class ViewType(val type: Int) {
        LOADING_VIEW(0), PICTURE_VIEW(1)
    }
}

object LoadingItem : AstronomyPictureRecyclerItem() {
    override fun isSameItem(other: RecyclerItem) = other is LoadingItem
    override fun isSameContent(other: RecyclerItem) = other == this
    override fun getType() = ViewType.LOADING_VIEW.type
    override fun getUniqueId() = -1L
}

data class PictureItem(val id: Long, val date: String, val picture: String, val name: String) :
    AstronomyPictureRecyclerItem() {
    override fun isSameItem(other: RecyclerItem) = other is PictureItem && other.id == id
    override fun isSameContent(other: RecyclerItem) = other is PictureItem && other == this
    override fun getType() = ViewType.PICTURE_VIEW.type
    override fun getUniqueId() = id
}

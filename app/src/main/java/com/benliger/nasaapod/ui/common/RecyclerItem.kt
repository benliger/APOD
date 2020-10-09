package com.benliger.nasaapod.ui.common

/**
 * Base class for Any RecyclerView Data.
 */
interface RecyclerItem {
    fun isSameItem(other: RecyclerItem): Boolean
    fun isSameContent(other: RecyclerItem): Boolean
    fun getType(): Int
    fun getUniqueId(): Long
}
package com.benliger.nasaapod.util

import android.content.Context
import com.benliger.nasaapod.R
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule


@GlideModule
class GlideModule : AppGlideModule() {

    companion object {
        private const val TO_KILO_BYTE = 1024
        private const val TO_BYTE = 1024
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memoryCacheSizeMBytes = context.resources.getInteger(R.integer.memoryCacheSizeMBytes)
        val bitmapPoolSizeMBytes = context.resources.getInteger(R.integer.bitmapPoolSizeMBytes)
        val diskCacheSizeMBytes = context.resources.getInteger(R.integer.diskCacheSizeMBytes)

        //Convert in byte
        val memoryCacheSizeBytes = memoryCacheSizeMBytes * TO_KILO_BYTE * TO_BYTE
        val bitmapPoolSizeBytes = bitmapPoolSizeMBytes * TO_KILO_BYTE * TO_BYTE
        val diskCacheSizeBytes = diskCacheSizeMBytes * TO_KILO_BYTE * TO_BYTE

        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
            .setBitmapPool(LruBitmapPool(bitmapPoolSizeBytes.toLong()))
            .setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
    }

    /**
     * If you’ve already migrated to the Glide v4 AppGlideModule and LibraryGlideModule,
     * you can disable manifest parsing entirely. Doing so can improve the initial startup
     * time of Glide and avoid some potential problems with trying to parse metadata.
     * To disable manifest parsing, override the isManifestParsingEnabled() method
     * in your AppGlideModule implementation:
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

}
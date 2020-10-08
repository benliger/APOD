package com.benliger.nasaapod.service.model

import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDate

data class Apod(

    @SerializedName("copyright")
    val copyright: String = "",

    @SerializedName("date")
    val date: String = LocalDate.now().toString(),

    @SerializedName("explanation")
    val explanation: String = "",

    @SerializedName("hdurl")
    val hdUrl: String = "",

    @SerializedName("media_type")
    val mediaType: String = "",

    @SerializedName("service_version")
    val serviceVersion: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("url")
    val url: String = ""
)
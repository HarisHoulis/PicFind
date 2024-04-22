package com.houlis.haris.picfind.data.pictures.impl.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PictureRaw(
    @SerialName("id")
    val id: String,
    @SerialName("secret")
    val secret: String,
    @SerialName("server")
    val server: String,
    @SerialName("title")
    val title: String,
)

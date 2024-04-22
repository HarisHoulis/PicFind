package com.houlis.haris.picfind.data.pictures.impl.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PicturesResponseRaw(
    @SerialName("photos")
    val pictures: PicturesRaw,
)

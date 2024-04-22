package com.houlis.haris.picfind.data.pictures.impl.model.datastore

import kotlinx.serialization.Serializable

@Serializable
internal data class PictureDataStore(
    val id: String,
    val basePath: BasePathDataStore,
    val title: String,
)

@Serializable
internal data class BasePathDataStore(val value: String)

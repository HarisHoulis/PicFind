package com.houlis.haris.picfind.data.pictures.impl

import com.houlis.haris.picfind.data.pictures.api.model.BasePath
import com.houlis.haris.picfind.data.pictures.api.model.Picture
import com.houlis.haris.picfind.data.pictures.impl.model.PictureRaw
import com.houlis.haris.picfind.data.pictures.impl.model.PicturesResponseRaw
import com.houlis.haris.picfind.data.pictures.impl.model.datastore.BasePathDataStore
import com.houlis.haris.picfind.data.pictures.impl.model.datastore.PictureDataStore

private const val FORWARD_SLASH = "/"
private const val UNDERSCORE = "_"

internal fun PicturesResponseRaw.toPictures(imageBaseUrl: String): List<Picture> =
    pictures.pictures.map { pictureRaw: PictureRaw ->
        Picture(
            id = pictureRaw.id,
            basePath = pictureRaw.buildBasePath(imageBaseUrl),
            title = pictureRaw.title
        )
    }

private fun PictureRaw.buildBasePath(imageBaseUrl: String) = BasePath(
    buildString {
        append(imageBaseUrl)
        append(server)
        append(FORWARD_SLASH)
        append(id)
        append(UNDERSCORE)
        append(secret)
    }
)

internal fun Picture.toPictureDataStore() = PictureDataStore(
    id = id,
    basePath = basePath.toBasePathDataStore(),
    title = title
)

private fun BasePath.toBasePathDataStore() = BasePathDataStore(value = this.toString())

internal fun PictureDataStore.toPicture() = Picture(
    id = id,
    basePath = basePath.toBasePath(),
    title = title
)

private fun BasePathDataStore.toBasePath() = BasePath(value = value)

package com.houlis.haris.picfind.data.pictures.impl

import com.houlis.haris.picfind.data.pictures.impl.model.PictureRaw
import com.houlis.haris.picfind.data.pictures.impl.model.PicturesRaw
import com.houlis.haris.picfind.data.pictures.impl.model.PicturesResponseRaw

private const val SERVER = "65535"

internal fun dummyPicturesResponseRaw(
    picturesRaw: List<PictureRaw> = listOf(dummyPictureRaw1(), dummyPictureRaw2(), dummyPictureRaw3()),
) = PicturesResponseRaw(
    PicturesRaw(picturesRaw)
)

internal fun dummyPictureRaw1() = PictureRaw(
    id = "12663187230",
    secret = "3c0b1465a7",
    server = SERVER,
    title = "Cat"
)

internal fun dummyPictureRaw2() = PictureRaw(
    id = "22662247857",
    secret = "acd1bfe3ab",
    server = SERVER,
    title = "Dog"
)

internal fun dummyPictureRaw3() = PictureRaw(
    id = "32663147230",
    secret = "9c0b14s5a7",
    server = SERVER,
    title = "Bird"
)

internal fun dummyPictureRaw4() = PictureRaw(
    id = "42662247857",
    secret = "acdssfe3ab",
    server = SERVER,
    title = "Snake"
)

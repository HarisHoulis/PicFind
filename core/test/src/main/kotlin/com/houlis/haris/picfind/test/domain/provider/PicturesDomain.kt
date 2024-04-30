package com.houlis.haris.picfind.test.domain.provider

import com.houlis.haris.picfind.data.pictures.api.model.BasePath
import com.houlis.haris.picfind.data.pictures.api.model.Picture

const val IMAGE_BASE_URL = "someurl.com"

fun dummyPictures() = listOf(
    dummyPicture1(),
    dummyPicture2()
)

fun dummyPicture1() = Picture(
    id = "dummyPicture1",
    basePath = BasePath(""),
    title = "Pic1"
)

fun dummyPicture2() = Picture(
    id = "dummyPicture2",
    basePath = BasePath(""),
    title = "Pic2"
)

fun dummyPicture3() = Picture(
    id = "dummyPicture3",
    basePath = BasePath(""),
    title = "Pic3"
)

fun dummyPicture4() = Picture(
    id = "dummyPicture4",
    basePath = BasePath(""),
    title = "Pic4"
)

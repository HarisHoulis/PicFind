package com.houlis.haris.picfind.data.pictures.api

import com.houlis.haris.picfind.data.pictures.api.model.Picture
import dev.forkhandles.result4k.Result

interface PicturesRepositoryContract {

    suspend fun searchFor(query: String): Result<List<Picture>, Throwable>

    suspend fun save(picture: Picture)

    suspend fun retrievePictureWith(id: String): Picture
}

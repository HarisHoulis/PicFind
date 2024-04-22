package com.houlis.haris.picfind.test.data

import com.houlis.haris.picfind.data.pictures.api.PicturesRepositoryContract
import com.houlis.haris.picfind.data.pictures.api.model.Picture
import com.houlis.haris.picfind.test.data.FakePicturesRepository.Query
import com.houlis.haris.picfind.test.domain.provider.dummyPicture1
import com.houlis.haris.picfind.test.domain.provider.dummyPicture2
import com.houlis.haris.picfind.test.domain.provider.dummyPicture3
import com.houlis.haris.picfind.test.domain.provider.dummyPicture4
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success

/**
 * Custom implementation of [PicturesRepositoryContract] for tests,
 * in order to manipulate result of [PicturesRepositoryContract.searchFor].
 *
 * Use one of the [Query] sub-types as a query.
 */
class FakePicturesRepository : PicturesRepositoryContract {

    private var exception: Exception? = null

    private var returnEmptyResponse: Boolean = false

    private val savedPics = mutableMapOf<String, Picture>()

    fun setEmptyResponse() {
        exception = null
        returnEmptyResponse = true
    }

    fun throwException() {
        returnEmptyResponse = false
        exception = UnsupportedOperationException()
    }

    override suspend fun searchFor(query: String): Result<List<Picture>, Throwable> {
        val e = exception
        return when {
            e != null -> Failure(Throwable())
            returnEmptyResponse -> Success(emptyList())
            else -> Success(query.mapToPictures())
        }
    }

    private fun String.mapToPictures(): List<Picture> = when (this) {
        Query.Query1.text -> listOf(dummyPicture1(), dummyPicture2())
        Query.Query2.text -> listOf(dummyPicture3(), dummyPicture4())
        else -> throw IllegalArgumentException("Have you used the appropriate Query?")
    }

    override suspend fun save(picture: Picture) {
        savedPics[picture.id] = picture
    }

    override suspend fun retrievePictureWith(id: String): Picture =
        savedPics.getValue(id)

    enum class Query(val text: String) {
        Query1("donut"), Query2("hole")
    }
}

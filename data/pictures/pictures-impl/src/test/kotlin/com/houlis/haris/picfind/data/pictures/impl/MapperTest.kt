package com.houlis.haris.picfind.data.pictures.impl

import com.houlis.haris.picfind.data.pictures.api.model.BasePath
import com.houlis.haris.picfind.data.pictures.api.model.Picture
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MapperTest {

    private companion object {
        private const val IMAGE_BASE_URL = "https://some.url.com/"
    }

    @Test
    fun name() {
        val result = dummyPicturesResponseRaw().toPictures(IMAGE_BASE_URL)

        expectThat(result).isEqualTo(
            listOf(
                Picture(
                    id = "12663187230",
                    basePath = expectedBasePath("12663187230", "3c0b1465a7"),
                    title = "Cat"
                ),
                Picture(
                    id = "22662247857",
                    basePath = expectedBasePath("22662247857", "acd1bfe3ab"),
                    title = "Dog"
                ),
                Picture(
                    id = "32663147230",
                    basePath = expectedBasePath("32663147230", "9c0b14s5a7"),
                    title = "Bird"
                )
            )
        )
    }

    private fun expectedBasePath(id: String, secret: String) = BasePath(
        buildString {
            append(IMAGE_BASE_URL)
            append("65535")
            append("/")
            append(id)
            append("_")
            append(secret)
        }
    )
}

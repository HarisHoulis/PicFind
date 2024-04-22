package com.houlis.haris.picfind.data.pictures.api.model

private const val UNDERSCORE = "_"
private const val THUMBNAIL_SUFFIX = "t"
private const val JPG_EXT = ".jpg"

data class Picture(
    val id: String,
    val basePath: BasePath,
    val title: String,
) {
    val thumbnail
        get() = buildString {
            append(basePath)
            append(UNDERSCORE)
            append(THUMBNAIL_SUFFIX)
            append(JPG_EXT)
        }

    val largeImage
        get() = buildString {
            append(basePath)
            append(JPG_EXT)
        }
}

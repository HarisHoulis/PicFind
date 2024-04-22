package com.houlis.haris.picfind.data.pictures.impl

import com.houlis.haris.picfind.data.pictures.impl.model.PicturesResponseRaw
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The production version of [PicturesApi], provided by [ApiProvider]
 */
internal interface PicturesService {

    @GET("?method=flickr.photos.search&format=json&per_page=25&nojsoncallback=1")
    suspend fun searchFor(
        @Query("text") query: String,
    ): Response<PicturesResponseRaw>
}

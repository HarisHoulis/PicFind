package com.houlis.haris.picfind.data.pictures.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.houlis.haris.picfind.core.result.fold
import com.houlis.haris.picfind.data.pictures.api.PicturesRepositoryContract
import com.houlis.haris.picfind.data.pictures.api.model.Picture
import com.houlis.haris.picfind.data.pictures.impl.di.ImageBaseUrl
import com.houlis.haris.picfind.data.pictures.impl.model.datastore.PictureDataStore
import com.houlis.haris.picfind.domain.network.safeApiCall
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.Success
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class PicturesRepository @Inject constructor(
    private val picturesService: PicturesService,
    @ImageBaseUrl
    private val imageBaseUrl: String,
    private val dataStore: DataStore<Preferences>,
    private val json: Json,
) : PicturesRepositoryContract {

    private val pictureKey = stringPreferencesKey("picture")

    override suspend fun searchFor(query: String): Result4k<List<Picture>, Throwable> = safeApiCall {
        picturesService.searchFor(query)
    }.fold(
        onSuccess = { response ->
            // TODO Add validation
            Success(response.toPictures(imageBaseUrl))
        },
        onFailure = { ex ->
            Failure(ex)
        }
    )

    override suspend fun save(picture: Picture) {
        dataStore.edit { prefs ->
            prefs[pictureKey] = json.encodeToString(picture.toPictureDataStore())
        }
    }

    override suspend fun retrievePictureWith(id: String): Picture =
        dataStore.data.map { prefs ->
            val serialized = prefs[pictureKey] ?: throw IllegalArgumentException("Picture with id $id not found!")
            json.decodeFromString<PictureDataStore>(serialized).toPicture()
        }.first()
}

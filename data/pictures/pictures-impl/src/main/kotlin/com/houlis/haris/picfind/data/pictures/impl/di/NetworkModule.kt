package com.houlis.haris.picfind.data.pictures.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.houlis.haris.picfind.data.pictures.impl.PicturesService
import com.houlis.haris.picfind.data.pictures.impl.api.interceptor.loggingInterceptor
import com.houlis.haris.picfind.data.pictures.impl.api.interceptor.tokenInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ImageBaseUrl

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    private const val BASE_URL = "https://api.flickr.com/services/rest/"

    private val mediaType = "application/json".toMediaType()

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesConverterFactory(json: Json): Converter.Factory = json.asConverterFactory(mediaType)

    @Provides
    @Singleton
    fun providesOkHttpClient() = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .build()

    @Provides
    @Singleton
    fun providesPicturesService(client: OkHttpClient, converterFactory: Converter.Factory): PicturesService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(PicturesService::class.java)

    @ImageBaseUrl
    @Provides
    @Singleton
    fun providesImageBaseUrl(): String = "https://live.staticflickr.com/"

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile("settings")
    }
}

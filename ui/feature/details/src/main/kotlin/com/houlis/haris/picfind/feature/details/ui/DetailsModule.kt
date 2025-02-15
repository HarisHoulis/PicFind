package com.houlis.haris.picfind.feature.details.ui

import com.houlis.haris.picfind.data.pictures.api.PicturesRepositoryContract
import com.houlis.haris.picfind.ui.common.mvi.MwProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object DetailsModule {

    @ViewModelScoped
    @Provides
    fun providesMiddlewares(repository: PicturesRepositoryContract): MwProvider<DetailsState, PicDetailsAction> =
        MwProvider { dispatcher ->
            listOf(
                DetailsMiddleware(repository, dispatcher)
            )
        }
}

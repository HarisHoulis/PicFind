package com.houlis.haris.picfind.feature.list.ui.viewmodel

import com.houlis.haris.picfind.data.pictures.api.PicturesRepositoryContract
import com.houlis.haris.picfind.ui.common.mvi.MwProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import java.time.Duration

@Module
@InstallIn(ViewModelComponent::class)
internal object PicturesModule {

    @ViewModelScoped
    @Provides
    fun providesInputDebounceDuration(): Duration = Duration.ofMillis(500)

    @ViewModelScoped
    @Provides
    fun providesMiddlewares(
        repository: PicturesRepositoryContract,
        debounce: Duration,
        scope: CoroutineScope,
    ): MwProvider<PicturesState, PicturesAction> = MwProvider { dispatcher ->
        listOf(
            PicturesMiddleware(
                repository = repository,
                inputDebounce = debounce,
                dispatcher = dispatcher,
                scope = scope
            )
        )
    }
}

package com.houlis.haris.picfind.domain.coroutines

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface CoroutinesModule {
    @Binds
    @Singleton
    fun bindCloseableCoroutineScope(closeableCoroutineScope: ProdCloseableCoroutineScope): CloseableCoroutineScope
}

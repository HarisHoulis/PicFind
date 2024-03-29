package com.houlis.haris.picfind.feature.list.ui

import com.houlis.haris.picfind.feature.list.ui.viewmodel.LoadState
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesState
import com.houlis.haris.picfind.test.domain.provider.dummyPicture1
import com.houlis.haris.picfind.test.domain.provider.dummyPicture2
import kotlinx.collections.immutable.persistentListOf

internal val expectedLoadingState = PicturesState(
    LoadState.Loading,
    persistentListOf(),
    null
)
internal val expectedLoadedState = PicturesState(
    LoadState.Loaded,
    persistentListOf(dummyPicture1(), dummyPicture2()),
    null
)
internal val expectedErrorState = PicturesState(
    LoadState.Error,
    persistentListOf(),
    null
)
internal val expectedNoResultsState = PicturesState(
    LoadState.NoResults,
    persistentListOf(),
    null
)
internal val expectedOnPicClickedState = PicturesState(
    LoadState.Loaded,
    persistentListOf(dummyPicture1(), dummyPicture2()),
    dummyPicture1()
)
internal val expectedPostNavigationState = PicturesState(
    LoadState.Loaded,
    persistentListOf(dummyPicture1(), dummyPicture2()),
    null
)

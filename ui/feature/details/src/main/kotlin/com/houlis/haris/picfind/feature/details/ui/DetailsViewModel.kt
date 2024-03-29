package com.houlis.haris.picfind.feature.details.ui

import com.houlis.haris.picfind.core.coroutines.CloseableCoroutineScope
import com.houlis.haris.picfind.feature.details.navigation.PicDetailsArg
import com.houlis.haris.picfind.feature.details.ui.PicDetailsAction.FetchDetailsFor
import com.houlis.haris.picfind.ui.common.mvi.MviViewModel
import com.houlis.haris.picfind.ui.common.mvi.MwProvider
import com.houlis.haris.picfind.ui.common.savedstate.ReadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    readState: ReadState<String>,
    scope: CloseableCoroutineScope,
    reducer: DetailsReducer,
    mwProvider: MwProvider<DetailsState, PicDetailsAction>,
) : MviViewModel<DetailsState, PicDetailsAction>(scope, reducer, mwProvider, DetailsState()) {

    private val picId = PicDetailsArg(readState).picId

    fun fetchDetails() {
        dispatch(FetchDetailsFor(picId))
    }
}

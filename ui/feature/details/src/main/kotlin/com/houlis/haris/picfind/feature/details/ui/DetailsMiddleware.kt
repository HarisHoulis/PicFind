package com.houlis.haris.picfind.feature.details.ui

import com.houlis.haris.picfind.data.pictures.api.PicturesRepositoryContract
import com.houlis.haris.picfind.feature.details.ui.PicDetailsAction.DetailsFetched
import com.houlis.haris.picfind.feature.details.ui.PicDetailsAction.FetchDetailsFor
import com.houlis.haris.picfind.ui.common.mvi.Dispatcher
import com.houlis.haris.picfind.ui.common.mvi.Middleware

internal class DetailsMiddleware(
    private val repository: PicturesRepositoryContract,
    dispatcher: Dispatcher<PicDetailsAction>,
) : Middleware<DetailsState, PicDetailsAction>(dispatcher) {

    override suspend fun process(state: DetailsState, action: PicDetailsAction) {
        when (action) {
            is FetchDetailsFor -> dispatch(DetailsFetched(repository.retrievePictureWith(action.imageId).largeImage))
            else -> {}
        }
    }
}

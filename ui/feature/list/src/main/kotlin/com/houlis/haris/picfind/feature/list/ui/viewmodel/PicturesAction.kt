package com.houlis.haris.picfind.feature.list.ui.viewmodel

import com.houlis.haris.picfind.data.pictures.api.model.Picture
import com.houlis.haris.picfind.ui.common.mvi.Action
import kotlinx.collections.immutable.ImmutableList

internal sealed interface PicturesAction : Action {
    data class SearchFor(val query: String) : PicturesAction
    data class OnPictureClicked(val picture: Picture) : PicturesAction
    data class PictureSaved(val picture: Picture) : PicturesAction
    data class PicturesLoaded(val pictures: ImmutableList<Picture>) :
        PicturesAction

    data object NoResults : PicturesAction
    data object Error : PicturesAction
    data object OnNavigated : PicturesAction
}

package com.houlis.haris.feature.details.ui

import com.houlis.haris.picfind.feature.details.ui.DetailsMiddleware
import com.houlis.haris.picfind.feature.details.ui.DetailsReducer
import com.houlis.haris.picfind.feature.details.ui.DetailsState
import com.houlis.haris.picfind.feature.details.ui.DetailsViewModel
import com.houlis.haris.picfind.feature.details.ui.LoadState.Loaded
import com.houlis.haris.picfind.feature.details.ui.LoadState.Loading
import com.houlis.haris.picfind.test.data.FakePicturesRepository
import com.houlis.haris.picfind.test.domain.provider.dummyPicture1
import com.houlis.haris.picfind.ui.common.savedstate.ReadState
import com.houlis.haris.picfind.ui.common.testutil.TestCloseableScope
import com.houlis.haris.picfind.ui.common.testutil.assertStatesFor
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

internal class DetailsViewModelTest {

    private val readState = ReadState {
        dummyPicture1().id
    }

    private val scope = TestCloseableScope()

    private fun sut(repository: FakePicturesRepository = FakePicturesRepository()) = DetailsViewModel(
        readState = readState,
        scope = scope,
        reducer = DetailsReducer(),
        mwProvider = { dispatcher ->
            listOf(DetailsMiddleware(repository, dispatcher, scope))
        }
    )

    @Test
    fun `fetches picture's details`() = runTest {
        val repository = FakePicturesRepository().apply {
            save(dummyPicture1())
        }
        val sut by lazy { sut(repository) }

        sut.assertStatesFor(
            DetailsState(),
            DetailsState(Loading, null),
            DetailsState(Loaded, dummyPicture1().image)
        ) {
            fetchDetails()
        }
    }
}

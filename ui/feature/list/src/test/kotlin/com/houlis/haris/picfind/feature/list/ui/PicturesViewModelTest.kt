package com.houlis.haris.picfind.feature.list.ui

import com.houlis.haris.picfind.data.pictures.api.PicturesRepositoryContract
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesMiddleware
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesReducer
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesState
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesViewModel
import com.houlis.haris.picfind.test.data.FakePicturesRepository
import com.houlis.haris.picfind.test.data.FakePicturesRepository.Query.Query1
import com.houlis.haris.picfind.test.domain.provider.dummyPicture1
import com.houlis.haris.picfind.ui.common.savedstate.SaveState
import com.houlis.haris.picfind.ui.common.testutil.TestCloseableScope
import com.houlis.haris.picfind.ui.common.testutil.assertStatesFor
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectThat
import strikt.assertions.hasEntry
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import java.time.Duration

internal class PicturesViewModelTest {

    private companion object {
        private const val PIC_ID_ARG = "PIC_ID"
    }

    private val scope = TestCloseableScope()

    private val savedStates = mutableMapOf<String, String>()
    private val saveState = SaveState(savedStates::put)

    private fun picturesRepository() = FakePicturesRepository()

    private fun sut(repo: PicturesRepositoryContract = picturesRepository()) = PicturesViewModel(
        picIdArg = PIC_ID_ARG,
        saveState = saveState,
        scope = scope,
        reducer = PicturesReducer(),
        mwProvider = { dispatcher ->
            listOf(
                PicturesMiddleware(
                    repository = repo,
                    inputDebounce = Duration.ofMillis(0),
                    dispatcher = dispatcher,
                    scope = scope
                )
            )
        }
    )

    @Test
    fun `loads pictures`() {
        sut().assertStatesFor(PicturesState(), expectedLoadingState, expectedLoadedState) {
            searchFor(Query1.text)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "a", "ab"])
    fun `ignores invalid query`(query: String) {
        sut().assertStatesFor(PicturesState(), expectedLoadingState) {
            searchFor(query)
        }
    }

    @Test
    fun `reports error when failing to load pictures`() {
        sut(picturesRepository().apply { throwException() }).assertStatesFor(
            PicturesState(),
            expectedLoadingState,
            expectedErrorState
        ) {
            searchFor(Query1.text)
        }
    }

    @Test
    fun `reports no results`() {
        sut(picturesRepository().apply { setEmptyResponse() }).assertStatesFor(
            PicturesState(),
            expectedLoadingState,
            expectedNoResultsState
        ) {
            searchFor(Query1.text)
        }
    }

    @Test
    fun `navigates to details screen`() = runTest {
        val picturesRepository = picturesRepository()

        sut(picturesRepository).assertStatesFor(
            PicturesState(),
            expectedLoadingState,
            expectedLoadedState,
            expectedOnPicClickedState,
            expectedPostNavigationState
        ) {
            searchFor(Query1.text)
            onPictureClicked(dummyPicture1())
            onNavigated()
        }

        expectThat(savedStates)
            .hasSize(1)
            .hasEntry(PIC_ID_ARG, dummyPicture1().id)
        expectThat(picturesRepository.retrievePictureWith(dummyPicture1().id))
            .isEqualTo(dummyPicture1())
    }
}

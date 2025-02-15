package com.houlis.haris.picfind.feature.list.ui

import app.cash.turbine.test
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.PicturesLoaded
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesAction.SearchFor
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesMiddleware
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesState
import com.houlis.haris.picfind.test.data.FakePicturesRepository
import com.houlis.haris.picfind.test.data.FakePicturesRepository.Query.Query1
import com.houlis.haris.picfind.test.data.FakePicturesRepository.Query.Query2
import com.houlis.haris.picfind.test.domain.provider.dummyPicture3
import com.houlis.haris.picfind.test.domain.provider.dummyPicture4
import com.houlis.haris.picfind.ui.common.testutil.TestDispatcher
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.time.Duration

internal class PicturesMiddlewareTest {

    private val coroutineScope = TestScope(UnconfinedTestDispatcher())

    private val testDispatcher = TestDispatcher<PicturesAction>()

    private fun sut(debounce: Duration = Duration.ofMillis(100)) = PicturesMiddleware(
        repository = FakePicturesRepository(),
        inputDebounce = debounce,
        dispatcher = testDispatcher,
        scope = coroutineScope.backgroundScope
    )

    @Test
    fun `debounces multiple queries`() = coroutineScope.runTest {
        val expectedPictures = persistentListOf(dummyPicture3(), dummyPicture4())

        with(sut()) {
            process(PicturesState(), SearchFor(Query1.text))
            process(PicturesState(), SearchFor(Query2.text))
        }

        testDispatcher.actionFlow.test {
            expectThat(awaitItem()).isEqualTo(PicturesLoaded(expectedPictures))
        }
    }
}

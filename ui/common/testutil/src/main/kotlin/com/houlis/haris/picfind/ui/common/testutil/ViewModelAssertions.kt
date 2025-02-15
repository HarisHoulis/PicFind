package com.houlis.haris.picfind.ui.common.testutil

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.houlis.haris.picfind.ui.common.mvi.Action
import com.houlis.haris.picfind.ui.common.mvi.MviViewModel
import com.houlis.haris.picfind.ui.common.mvi.State
import strikt.api.expectThat
import strikt.assertions.isEqualTo

suspend fun <S : State, A : Action, VM : MviViewModel<S, A>> VM.assertStatesFor(
    vararg expectedStates: S,
    f: VM.() -> Unit = {},
) {
    state.test {
        f()

        check(*expectedStates)
    }
}

suspend fun <S : State> TurbineTestContext<S>.check(vararg expectedStates: S) {
    expectedStates.forEach { expectedState ->
        expectThat(awaitItem()).isEqualTo(expectedState)
    }
}

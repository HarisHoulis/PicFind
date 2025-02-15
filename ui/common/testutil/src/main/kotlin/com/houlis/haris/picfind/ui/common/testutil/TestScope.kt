package com.houlis.haris.picfind.ui.common.testutil

import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher

val testScope: TestScope
    get() = TestScope(UnconfinedTestDispatcher())

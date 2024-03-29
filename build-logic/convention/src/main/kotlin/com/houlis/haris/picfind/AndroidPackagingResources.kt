package com.houlis.haris.picfind

import com.android.build.api.dsl.CommonExtension

internal fun CommonExtension<*, *, *, *, *, *>.configurePackagingResources() {
    packaging {
        resources {
            excludes.add("META-INF/LICENSE.md")
            excludes.add("META-INF/LICENSE-notice.md")
        }
    }
}

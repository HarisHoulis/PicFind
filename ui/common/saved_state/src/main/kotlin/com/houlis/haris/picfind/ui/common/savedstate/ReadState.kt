package com.houlis.haris.picfind.ui.common.savedstate

/**
 * Provides a way to read from any storage system, by abstracting the implementation details
 */
fun interface ReadState<T> : (String) -> T

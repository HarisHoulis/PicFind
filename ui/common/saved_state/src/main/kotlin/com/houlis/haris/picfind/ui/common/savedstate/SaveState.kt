package com.houlis.haris.picfind.ui.common.savedstate

/**
 * Provides a way to save to any storage system, by abstracting the implementation details
 */
fun interface SaveState<T> : (String, T) -> Unit

package com.houlis.haris.picfind.data.pictures.api.model

@JvmInline
value class BasePath(private val value: String) : CharSequence by value {
    override fun toString(): String {
        return value
    }
}

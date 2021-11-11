/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 11/08/21, 11:11 AM
 */

package com.example.assignment.common.Utills

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
@Parcelize

open class Event<out T>(private val content: @RawValue T) : Parcelable {

    @IgnoredOnParcel
    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 11/08/21, 11:16 AM
 */

package com.example.assignment.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(

    @Json(name = "data")
    val data: T?,

    @Json(name = "success")
    val success: Boolean,

    @Json(name = "message")
    val message: String,
)
package com.example.assignment.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(

    @Json(name = "results")
    val data: T?
)
package com.example.assignment.data.model

import com.example.assignment.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultParams(
    @Json(name = BuildConfig.RESULT)
    var resultKeyword: Int = 10
) {

    fun onDataClear() {
        resultKeyword = 10
    }
}
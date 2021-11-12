package com.example.assignment.data.model

import com.example.assignment.data.remote.NetworkingConstant.API_RESULT
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultParams(
    @Json(name = API_RESULT)
    var resultKeyword: Int = 10
) {

    fun onDataClear() {
        resultKeyword = 10
    }
}
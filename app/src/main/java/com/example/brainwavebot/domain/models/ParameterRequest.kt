package com.example.brainwavebot.domain.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ParameterRequest(
    @Json(name = "model")
    val model: String = "text-davinci-003",
    @Json(name = "prompt")
    var prompt: String
)

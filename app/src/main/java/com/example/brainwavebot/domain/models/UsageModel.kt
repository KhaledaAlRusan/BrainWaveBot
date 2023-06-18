package com.example.brainwavebot.domain.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsageModel(
    @Json(name = "completion_tokens")
    val completionTokens: Int,
    @Json(name = "prompt_tokens")
    val promptTokens: Int,
    @Json(name = "total_tokens")
    val totalTokens: Int
)

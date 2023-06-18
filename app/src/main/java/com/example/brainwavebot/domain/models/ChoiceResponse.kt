package com.example.brainwavebot.domain.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChoiceResponse(
//    @PrimaryKey(autoGenerate = true)
//    var id: Long? = null,

    @Json(name = "text")
    val text: String,

    var searchTime: Long = 0,

    var userType: String = "BOT"
)

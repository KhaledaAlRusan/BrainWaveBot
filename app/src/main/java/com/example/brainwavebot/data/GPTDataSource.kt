package com.example.brainwavebot.data

import com.example.brainwavebot.domain.models.GPTResponse
import com.example.brainwavebot.domain.models.ParameterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface GPTDataSource {

    @POST("completions")
    suspend fun getAnswer(@Body parameterRequest: ParameterRequest): GPTResponse

}
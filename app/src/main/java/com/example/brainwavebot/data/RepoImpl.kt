package com.example.brainwavebot.data

import com.example.brainwavebot.domain.models.ParameterRequest
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val gptDataSource: GPTDataSource
){

    suspend fun getAnswer(text:String) =
        gptDataSource.getAnswer(ParameterRequest(prompt = text)).choiceResponses[0].text
}
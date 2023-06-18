package com.example.brainwavebot.presentation.features.chatting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainwavebot.data.RepoImpl
import com.example.brainwavebot.domain.models.ChoiceResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val repoImpl: RepoImpl
) : ViewModel() {

    private val _answerSharedFlow = MutableSharedFlow<String>()
    val answerStateFlow = _answerSharedFlow.asSharedFlow()

    private val _answerLoadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val answerLoadingStateFlow = _answerLoadingStateFlow

    private val _answerErrorStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val answerErrorStateFlow = _answerErrorStateFlow

    val messages = mutableListOf<ChoiceResponse>()

    fun requestGptResponse(text: String) {
        viewModelScope.launch {
            _answerLoadingStateFlow.emit(true)
            try {
                //user input
                _answerSharedFlow.emit(repoImpl.getAnswer(text))
            } catch (e: Exception) {
                _answerErrorStateFlow.emit(e)
                Log.d("TAG00", "")
            }
            _answerLoadingStateFlow.emit(false)
        }
    }

}
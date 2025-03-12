package com.pral.jetpackgemini.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.pral.jetpackgemini.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PromptViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = BuildConfig.API_KEY
    )

    fun sendPrompt(prompt: String){
        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
                        text(prompt)
                    })
                response.text?.let { outputContent ->
                    withContext(Dispatchers.Main) {
                        _uiState.value = UiState.Success(outputContent)
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    _uiState.value = UiState.Error(e.localizedMessage ?: "")
                }
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Initial
    }
}
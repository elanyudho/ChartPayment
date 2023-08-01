package com.example.core.abstraction

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T> : ViewModel() {
    protected val _uiState: MutableStateFlow<T> = MutableStateFlow(initialUiState)

    val uiState: StateFlow<T>
        get() = _uiState.asStateFlow()

    protected abstract val initialUiState: T
}
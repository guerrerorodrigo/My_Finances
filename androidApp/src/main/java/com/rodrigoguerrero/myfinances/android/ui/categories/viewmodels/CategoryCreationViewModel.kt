package com.rodrigoguerrero.myfinances.android.ui.categories.viewmodels

import androidx.lifecycle.ViewModel
import com.rodrigoguerrero.myfinances.android.ui.categories.models.CategoryCreationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CategoryCreationViewModel : ViewModel() {

    private val _state = MutableStateFlow(CategoryCreationUiState())
    val state: StateFlow<CategoryCreationUiState> = _state

    fun onIconPositionSelected(position: Int) {
        _state.update { it.copy(iconPosition = position) }
    }
}

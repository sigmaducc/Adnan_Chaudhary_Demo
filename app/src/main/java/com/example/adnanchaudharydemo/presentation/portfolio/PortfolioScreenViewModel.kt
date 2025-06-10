package com.example.adnanchaudharydemo.presentation.portfolio

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adnanchaudharydemo.domain.usecases.GetHoldingsUseCase
import com.example.adnanchaudharydemo.presentation.portfolio.uistates.PortfolioUiState
import com.example.adnanchaudharydemo.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PortfolioScreenViewModel @Inject constructor(
    private val getHoldingsUseCase: GetHoldingsUseCase,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow(
        PortfolioUiState(
            isPnLCollapsed = true,
            userHolding = Resource.Loading
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        fetchHoldings()
    }

    fun fetchHoldings() {
        _uiState.value = _uiState.value.copy(
            userHolding = Resource.Loading,
        )

        viewModelScope.launch {
            withContext(dispatcher) {
                try {
                    val uiUserHoldings = getHoldingsUseCase.execute()

                    _uiState.value = _uiState.value.copy(
                        userHolding = Resource.Success(uiUserHoldings),
                    )
                } catch (e: Exception) {
                    println("error: ${e.message}")
                    _uiState.value = _uiState.value.copy(
                        userHolding = Resource.Error("An error occurred: ${e.message}")
                    )
                }
            }
        }
    }

    fun togglePnL() {
        _uiState.value = _uiState.value.copy(
            isPnLCollapsed = !_uiState.value.isPnLCollapsed
        )
    }
}

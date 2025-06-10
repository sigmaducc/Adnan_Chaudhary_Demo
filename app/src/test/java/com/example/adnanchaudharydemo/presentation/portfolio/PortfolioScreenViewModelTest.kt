package com.example.adnanchaudharydemo.presentation.portfolio

import com.example.adnanchaudharydemo.domain.mappers.TextValueObjectMapper
import com.example.adnanchaudharydemo.domain.usecases.GetHoldingsUseCase
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiHolding
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiUserHolding
import com.example.adnanchaudharydemo.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PortfolioScreenViewModelTest {
    private lateinit var viewModel: PortfolioScreenViewModel
    private lateinit var getHoldingsUseCase: GetHoldingsUseCase
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var textValueObjectMapper: TextValueObjectMapper

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getHoldingsUseCase = mockk()
        viewModel = PortfolioScreenViewModel(getHoldingsUseCase, testDispatcher)
        textValueObjectMapper = TextValueObjectMapper()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetchHoldings is successful, update state with success`() = runTest {
        // Given
        val mockHoldings = UiUserHolding()

        coEvery { getHoldingsUseCase.execute() } returns mockHoldings

        // When
        viewModel.fetchHoldings()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertTrue(state.userHolding is Resource.Success)
        assertEquals(mockHoldings, (state.userHolding as Resource.Success<UiUserHolding>).data)
    }

    @Test
    fun `when fetchHoldings fails, update state with error`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { getHoldingsUseCase.execute() } throws Exception(errorMessage)

        // When
        viewModel.fetchHoldings()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertTrue(state.userHolding is Resource.Error)
        assertEquals(
            "An error occurred: $errorMessage",
            (state.userHolding as Resource.Error).message
        )
    }

    @Test
    fun `when togglePnL is called, update isPnLCollapsed state`() = runTest {
        // Given
        val initialState = viewModel.uiState.value
        assertTrue(initialState.isPnLCollapsed)

        // When
        viewModel.togglePnL()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val newState = viewModel.uiState.value
        assertTrue(!newState.isPnLCollapsed)
    }

    @Test
    fun `when fetchHoldings is called, initial state is loading`() = runTest {
        // Given
        val mockHoldings = UiUserHolding(
            holdings = listOf(
                UiHolding(
                    symbol = "MAHABANK",
                    quantity = 990,
                    ltp = textValueObjectMapper.map(38.05),
                    avgPrice = textValueObjectMapper.map(35.0),
                    close = textValueObjectMapper.map(40.0),
                    pnl = textValueObjectMapper.map(3019.5)
                )
            ),
            currentValue = textValueObjectMapper.map(37669.5),
            totalInvestment = textValueObjectMapper.map(34650.0),
            totalPnL = textValueObjectMapper.map(3019.5),
            todaysPnL = textValueObjectMapper.map(1930.5)
        )

        coEvery { getHoldingsUseCase.execute() } returns mockHoldings

        // When
        viewModel.fetchHoldings()
        
        // Then
        val state = viewModel.uiState.value
        assertTrue(state.userHolding is Resource.Loading)
        
        // When
        testDispatcher.scheduler.advanceUntilIdle()
        
        // Then
        val finalState = viewModel.uiState.value
        assertTrue(finalState.userHolding is Resource.Success)
    }
}
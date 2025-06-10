package com.example.adnanchaudharydemo.domain.usecases

import com.example.adnanchaudharydemo.data.models.HoldingResponse
import com.example.adnanchaudharydemo.data.models.UserHoldingDataDto
import com.example.adnanchaudharydemo.data.models.UserHoldingDto
import com.example.adnanchaudharydemo.domain.mappers.TextValueObjectMapper
import com.example.adnanchaudharydemo.domain.repositories.IPortfolioRepository
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiHolding
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiUserHolding
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.collections.get

class GetHoldingsUseCaseTest {

    private lateinit var useCase: GetHoldingsUseCase
    private lateinit var repository: IPortfolioRepository
    private lateinit var textValueObjectMapper: TextValueObjectMapper

    @Before
    fun setup() {
        repository = mockk()
        textValueObjectMapper = TextValueObjectMapper()
        useCase = GetHoldingsUseCase(repository, textValueObjectMapper)
    }

    @Test
    fun `when getHoldings is successful, calculate individual holding PnL correctly`() = runBlocking {
        // Given
        val mockResponse = HoldingResponse(
            data = UserHoldingDataDto(
                userHolding = listOf(
                    UserHoldingDto(
                        symbol = "MAHABANK",
                        quantity = 990,
                        ltp = 38.05,
                        avgPrice = 35.0,
                        close = 40.0
                    )
                )
            )
        )

        val expectedHolding = UiHolding(
            symbol = "MAHABANK",
            quantity = 990,
            ltp = textValueObjectMapper.map(38.05),
            avgPrice = textValueObjectMapper.map(35.0),
            close = textValueObjectMapper.map(40.0),
            pnl = textValueObjectMapper.map(3019.5) // (38.05 - 35.0) * 990
        )

        coEvery { repository.getHoldings() } returns mockResponse

        // When
        val result = useCase.execute()

        // Then
        assertEquals(1, result.holdings.size)
        assertEquals(expectedHolding, result.holdings[0])
    }

    @Test
    fun `when getHoldings is successful, calculate total investment correctly`() = runBlocking {
        // Given
        val mockResponse = HoldingResponse(
            data = UserHoldingDataDto(
                userHolding = listOf(
                    UserHoldingDto(
                        symbol = "MAHABANK",
                        quantity = 990,
                        ltp = 38.05,
                        avgPrice = 35.0,
                        close = 40.0
                    ),
                    UserHoldingDto(
                        symbol = "ICICI",
                        quantity = 100,
                        ltp = 118.25,
                        avgPrice = 110.0,
                        close = 105.0
                    )
                )
            )
        )

        coEvery { repository.getHoldings() } returns mockResponse

        // When
        val result = useCase.execute()

        // Then
        assertEquals(textValueObjectMapper.map(45650.0), result.totalInvestment) // (35.00 * 990) + (110.0 * 100)
    }

    @Test
    fun `when getHoldings is successful, calculate current value correctly`() = runBlocking {
        // Given
        val mockResponse = HoldingResponse(
            data = UserHoldingDataDto(
                userHolding = listOf(
                    UserHoldingDto(
                        symbol = "MAHABANK",
                        quantity = 990,
                        ltp = 38.05,
                        avgPrice = 35.0,
                        close = 40.0
                    ),
                    UserHoldingDto(
                        symbol = "ICICI",
                        quantity = 100,
                        ltp = 118.25,
                        avgPrice = 110.0,
                        close = 105.0
                    )
                )
            )
        )

        coEvery { repository.getHoldings() } returns mockResponse

        // When
        val result = useCase.execute()

        // Then
        assertEquals(textValueObjectMapper.map(49494.5), result.currentValue) // (38.05 * 990) + (118.25 * 100)
    }

    @Test
    fun `when getHoldings is successful, calculate total PnL correctly`() = runBlocking {
        // Given
        val mockResponse = HoldingResponse(
            data = UserHoldingDataDto(
                userHolding = listOf(
                    UserHoldingDto(
                        symbol = "MAHABANK",
                        quantity = 990,
                        ltp = 38.05,
                        avgPrice = 35.0,
                        close = 40.0
                    ),
                    UserHoldingDto(
                        symbol = "ICICI",
                        quantity = 100,
                        ltp = 118.25,
                        avgPrice = 110.0,
                        close = 105.0
                    )
                )
            )
        )

        coEvery { repository.getHoldings() } returns mockResponse

        // When
        val result = useCase.execute()

        // Then
        assertEquals(textValueObjectMapper.map(3844.5), result.totalPnL) // ((38.05 * 990) + (118.25 * 100)) - ((35.00 * 990) + (110.0 * 100))
    }

    @Test
    fun `when getHoldings is successful, calculate today's PnL correctly`() = runBlocking {
        // Given
        val mockResponse = HoldingResponse(
            data = UserHoldingDataDto(
                userHolding = listOf(
                    UserHoldingDto(
                        symbol = "MAHABANK",
                        quantity = 990,
                        ltp = 38.05,
                        avgPrice = 35.0,
                        close = 40.0
                    ),
                    UserHoldingDto(
                        symbol = "ICICI",
                        quantity = 100,
                        ltp = 118.25,
                        avgPrice = 110.0,
                        close = 105.0
                    )
                )
            )
        )

        coEvery { repository.getHoldings() } returns mockResponse

        // When
        val result = useCase.execute()

        // Then
        assertEquals(textValueObjectMapper.map(605.5), result.todaysPnL) // ((40.0 - 38.05) * 990) + ((105.0 - 118.25) * 100)
    }

    @Test
    fun `when getHoldings is successful, individual PnLs should sum up to total PnL`() = runBlocking {
        // Given
        val mockResponse = HoldingResponse(
            data = UserHoldingDataDto(
                userHolding = listOf(
                    UserHoldingDto(
                        symbol = "MAHABANK",
                        quantity = 990,
                        ltp = 38.05,
                        avgPrice = 35.0,
                        close = 40.0
                    ),
                    UserHoldingDto(
                        symbol = "ICICI",
                        quantity = 100,
                        ltp = 118.25,
                        avgPrice = 110.0,
                        close = 105.0
                    )
                )
            )
        )

        coEvery { repository.getHoldings() } returns mockResponse

        // When
        val result = useCase.execute()

        // Then
        val sumOfIndividualPnLs = result.holdings.sumOf { it.pnl.value }

        assertEquals(result.totalPnL.value, sumOfIndividualPnLs, 0.01)
    }

    @Test
    fun `when getHoldings returns empty list, return empty list`() = runBlocking {
        // Given
        val mockResponse = HoldingResponse(data = UserHoldingDataDto(userHolding = emptyList()))
        coEvery { repository.getHoldings() } returns mockResponse

        // When
        val result = useCase.execute()

        // Then
        assertEquals(emptyList<UiHolding>(), result.holdings)
        assertEquals(0.0, result.currentValue.value, 0.01)
        assertEquals(0.0, result.totalInvestment.value, 0.01)
        assertEquals(0.0, result.todaysPnL.value, 0.01)
        assertEquals(0.0, result.totalPnL.value, 0.01)
    }
} 
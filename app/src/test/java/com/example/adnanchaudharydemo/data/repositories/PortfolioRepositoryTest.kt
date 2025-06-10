package com.example.adnanchaudharydemo.data.repositories

import com.example.adnanchaudharydemo.data.datasource.remote.IPortfolioRemoteDataSource
import com.example.adnanchaudharydemo.data.models.HoldingResponse
import com.example.adnanchaudharydemo.data.models.UserHoldingDataDto
import com.example.adnanchaudharydemo.data.models.UserHoldingDto
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PortfolioRepositoryTest {

    private lateinit var repository: PortfolioRepository
    private lateinit var remoteDataSource: IPortfolioRemoteDataSource

    @Before
    fun setup() {
        remoteDataSource = mockk()
        repository = PortfolioRepository(remoteDataSource)
    }

    @Test
    fun `when getHoldings fails, throw exception`() = runBlocking {
        // Given
        coEvery { remoteDataSource.getHoldings() } returns Response.error(404, mockk(relaxed = true))

        // When
        try {
            repository.getHoldings()
            fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            // Then
            assertEquals("getHoldings API Failed", e.message)
        }
    }

    @Test
    fun `when getHoldings is successful, return holdings`() = runBlocking {
        // Given
        val mockResponse = HoldingResponse(
            data = UserHoldingDataDto(
                userHolding = listOf(
                    UserHoldingDto(
                        symbol = "AAPL",
                        quantity = 10,
                        ltp = 150.0,
                        avgPrice = 140.0,
                        close = 150.0
                    )
                )
            )
        )
        coEvery { remoteDataSource.getHoldings() } returns Response.success(mockResponse)

        // When
        val result = repository.getHoldings()

        // Then
        assertEquals(mockResponse, result)
    }
} 
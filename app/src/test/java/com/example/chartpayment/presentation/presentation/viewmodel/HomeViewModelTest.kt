package com.example.chartpayment.presentation.presentation.viewmodel

import com.example.core.abstraction.UseCase
import com.example.core.dispatcher.DispatcherProvider
import com.example.core.model.model.Charts
import com.example.core.model.model.History
import com.example.core.model.model.PaymentType
import com.example.core.model.usecase.GetChartsUseCase
import com.example.core.util.exception.Failure
import com.example.core.util.vo.Either
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    // The TestCoroutineDispatcher will be used to control the execution of coroutines in the test
    private val testDispatcher = TestCoroutineDispatcher()

    // The class under test
    private lateinit var homeViewModel: HomeViewModel

    // Mocked dependencies
    @Mock
    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    private lateinit var getChartsUseCase: GetChartsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        // Set up the mocked dispatcherProvider to return the testDispatcher
        Mockito.`when`(dispatcherProvider.io).thenReturn(testDispatcher)
        Mockito.`when`(dispatcherProvider.main).thenReturn(testDispatcher)

        // Create the ViewModel instance with the mocked dependencies
        homeViewModel = HomeViewModel(dispatcherProvider, getChartsUseCase)
    }

    @Test
    fun `getChart should set Success uiState when use case returns data`() = runTest {
        // Given
        val testData = Charts(dataLineChart = listOf(1,2,3,4,5,6,7,8,9,10,11,12), dataPieChart = listOf(
            PaymentType("Gopay", "55", listOf(History("22/1/2022", "55000")))
        ))
        val successEither: Either<Failure, Charts> = Either.Success(testData)

        Mockito.`when`(getChartsUseCase.run(UseCase.None)).thenReturn(successEither)

        assert(homeViewModel.uiState.value is HomeViewModel.MainUiState.Loading)

        // When
        homeViewModel.getChart()

        // Advance the TestCoroutineDispatcher to execute coroutines
        testDispatcher.scheduler.advanceUntilIdle()

        val successState = homeViewModel.uiState.value as HomeViewModel.MainUiState.Success

        assert(successState.data == testData)

    }
}
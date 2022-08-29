package com.example.gameofthones_mvvm_coroutines_retrofit_binding

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.model.Got
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.remote.RetrofitService
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.repository.MainRepository
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.activity.MainViewModel
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.state.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {


    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: MainViewModel

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = mock(MainRepository::class.java)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getAllGotTest() {
        runBlocking {
            Mockito.`when`(mainRepository.getAllGot())
                .thenReturn(NetworkState.Success(listOf<Got>(Got("got", ""))))
            mainViewModel.getAllGot()
            val result = mainViewModel.gotList.getOrAwaitValue()
            assertEquals(listOf<Got>(Got("got", "")), result)
        }
    }


    @Test
    fun `empty got list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getAllGot())
                .thenReturn(NetworkState.Success(listOf<Got>()))
            mainViewModel.getAllGot()
            val result = mainViewModel.gotList.getOrAwaitValue()
            assertEquals(listOf<Got>(), result)
        }
    }

}
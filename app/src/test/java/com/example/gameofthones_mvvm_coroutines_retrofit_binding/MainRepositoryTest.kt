package com.example.gameofthones_mvvm_coroutines_retrofit_binding

import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.model.Got
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.remote.RetrofitService
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.repository.MainRepository
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.state.NetworkState
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MainRepositoryTest {

    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var apiService: RetrofitService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(apiService)
    }

    @Test
    fun `get all got test`() {
        runBlocking {
            Mockito.`when`(apiService.getAllGot()).thenReturn(Response.success(listOf<Got>()))
            val response = mainRepository.getAllGot()
            assertEquals(listOf<Got>(), NetworkState.Success(response).data)
        }

    }

}
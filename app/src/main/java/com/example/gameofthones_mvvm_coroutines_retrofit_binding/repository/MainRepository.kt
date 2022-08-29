package com.example.gameofthones_mvvm_coroutines_retrofit_binding.repository

import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.model.Got
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.remote.RetrofitService
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.state.NetworkState

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllGot(): NetworkState<List<Got>> {
        val response = retrofitService.getAllGot()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}
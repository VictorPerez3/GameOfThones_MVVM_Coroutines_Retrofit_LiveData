package com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.remote

import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.model.Got
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("https://thronesapi.com/api/v2/Characters/")
    suspend fun getAllGot(): Response<List<Got>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://thronesapi.com/api/v2/Characters/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}
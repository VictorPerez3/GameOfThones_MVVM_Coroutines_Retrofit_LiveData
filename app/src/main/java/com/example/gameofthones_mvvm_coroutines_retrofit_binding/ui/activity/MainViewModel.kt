package com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.model.Got
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.repository.MainRepository
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.state.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    val gotList = MutableLiveData<List<Got>>()

    private var job: Job? = null


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllGot() {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getAllGot()) {
                is NetworkState.Success -> {
                    gotList.postValue(response.data)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        //gotList.postValue(NetworkState.Error())
                    } else {
                        //gotList.postValue(NetworkState.Error)
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
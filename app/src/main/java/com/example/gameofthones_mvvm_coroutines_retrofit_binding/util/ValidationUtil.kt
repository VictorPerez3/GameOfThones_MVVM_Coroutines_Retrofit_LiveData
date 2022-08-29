package com.example.gameofthones_mvvm_coroutines_retrofit_binding.util

import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.model.Got

object ValidationUtil {

    fun validateGot(got: Got): Boolean {
        if (got.fullName.isNotEmpty() && got.imageUrl.isNotEmpty()) {
            return true
        }
        return false
    }
}
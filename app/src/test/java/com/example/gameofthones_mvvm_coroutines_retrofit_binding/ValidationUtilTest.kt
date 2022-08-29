package com.example.gameofthones_mvvm_coroutines_retrofit_binding

import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.model.Got
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.util.ValidationUtil
import org.junit.Assert
import org.junit.Test

class ValidationUtilTest {

    @Test
    fun validateGotTest() {
        val got = Got("test", "testUrl")
        Assert.assertEquals(true, ValidationUtil.validateGot(got))
    }

    @Test
    fun validateGotEmptyTest() {
        val got = Got("", "testUrl")
        Assert.assertEquals(false, ValidationUtil.validateGot(got))
    }

}
package com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.data.remote.RetrofitService
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.databinding.ActivityMainBinding
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.repository.MainRepository
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.repository.MyViewModelFactory
import com.example.gameofthones_mvvm_coroutines_retrofit_binding.ui.adapter.GotAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val adapter = GotAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(mainRepository))[MainViewModel::class.java]


        viewModel.gotList.observe(this) {
            adapter.setGot(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this) {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        }

        viewModel.getAllGot()

    }
}
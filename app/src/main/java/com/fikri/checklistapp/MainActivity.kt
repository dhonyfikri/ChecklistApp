package com.fikri.checklistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fikri.checklistapp.databinding.ActivityMainBinding
import com.fikri.checklistapp.view_model.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.login("sixdhonyreff", "dhony123")
    }
}
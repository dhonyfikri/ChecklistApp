package com.fikri.checklistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
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

        viewModel.apply {
            loginData.observe(this@MainActivity) {
                when (it) {
                    is ApiResultWrapper.Success -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Token: ${it.response.data?.token.toString()}",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    }
                    else -> {
                        Toast.makeText(this@MainActivity, "Login Gagal", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.apply {
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                viewModel.login(username, password)
            }

            btnToRegister.setOnClickListener {
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            }
        }
    }
}
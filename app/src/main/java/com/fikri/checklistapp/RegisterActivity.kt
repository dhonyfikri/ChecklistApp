package com.fikri.checklistapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.databinding.ActivityRegisterBinding
import com.fikri.checklistapp.view_model.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.apply {
            registerData.observe(this@RegisterActivity) {
                when (it) {
                    is ApiResultWrapper.Success -> {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Register Success",
                            Toast.LENGTH_SHORT
                        ).show()
                        onBackPressed()
                    }
                    else -> {
                        Toast.makeText(this@RegisterActivity, "Register Gagal", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        binding.apply {
            btnRegister.setOnClickListener {
                val email = etEmail.text.toString()
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                viewModel.register(email, username, password)
            }

            btnToLogin.setOnClickListener {
                onBackPressed()
            }
        }
    }
}
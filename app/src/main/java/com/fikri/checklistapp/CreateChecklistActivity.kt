package com.fikri.checklistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fikri.checklistapp.databinding.ActivityCreateChecklistBinding

class CreateChecklistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateChecklistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
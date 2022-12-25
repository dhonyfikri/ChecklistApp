package com.fikri.checklistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.databinding.ActivityCreateChecklistBinding
import com.fikri.checklistapp.view_model.CreateChecklistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateChecklistActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TOKEN = "extra_token"
    }

    private lateinit var binding: ActivityCreateChecklistBinding
    private val viewModel: CreateChecklistViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
        setupAction()
    }

    private fun setupData() {
        if (intent.getParcelableExtra<Token>(EXTRA_TOKEN) != null) {
            viewModel.token = intent.getParcelableExtra<Token>(EXTRA_TOKEN) as Token
        }

        title = getString(R.string.add_new_checklist)

        viewModel.createChecklistListResponse.observe(this@CreateChecklistActivity) {
            when (it) {
                is ApiResultWrapper.Success -> {
                    Toast.makeText(this@CreateChecklistActivity, it.message, Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent()
                    intent.putExtra(HomeActivity.CREATE_CHECKLIST_STATUS_RESULT, true)
                    setResult(HomeActivity.CREATE_CHECKLIST_RESULT, intent)
                    finish()
                }
                is ApiResultWrapper.Error -> {
                    Toast.makeText(this@CreateChecklistActivity, it.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ApiResultWrapper.NetworkError -> {
                    Toast.makeText(
                        this@CreateChecklistActivity,
                        "Connection Failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                else -> {
                    // do something
                }
            }
        }
    }

    private fun setupAction() {
        binding.apply {
            btnSave.setOnClickListener {
                val name = etChecklistName.text.toString().trim()
                if (name.isNotEmpty()) {
                    viewModel.saveNewChecklist(name)
                }
            }
        }
    }
}

package com.fikri.checklistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.core.ui.adapter.ChecklistListAdapter
import com.fikri.checklistapp.databinding.ActivityHomeBinding
import com.fikri.checklistapp.view_model.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    companion object {
        const val CREATE_CHECKLIST_RESULT = 100
        const val CREATE_CHECKLIST_STATUS_RESULT = "create_checklist_status_result"
        const val EXTRA_TOKEN = "extra_token"
    }

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
        setupAction()
    }

    private fun setupData() {
        binding.apply {
            rvChecklistList.setHasFixedSize(true)
            rvChecklistList.layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
        }

        if (intent.getParcelableExtra<Token>(EXTRA_TOKEN) != null) {
            viewModel.token = intent.getParcelableExtra<Token>(EXTRA_TOKEN) as Token
        }

        viewModel.checklistList.observe(this) {
            when (it) {
                is Resource.Success -> {
                    setChecklistList(it.data as ArrayList<Checklist>)
                }
                is Resource.Error -> {
                    Toast.makeText(this@HomeActivity, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.NetworkError -> {
                    Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // to do something
                }
            }
        }
    }

    private fun setupAction() {
        binding.apply {
            fabCreateNewChecklist.setOnClickListener {
                val moveCreateChecklist =
                    Intent(this@HomeActivity, CreateChecklistActivity::class.java)
                moveCreateChecklist.putExtra(CreateChecklistActivity.EXTRA_TOKEN, viewModel.token)
                launcherIntentCreateChecklist.launch(
                    Intent(moveCreateChecklist)
                )
            }
        }
    }

    private val launcherIntentCreateChecklist = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == CREATE_CHECKLIST_RESULT) {
            val isSuccess = result.data?.getBooleanExtra(CREATE_CHECKLIST_STATUS_RESULT, false)
            if (isSuccess != null && isSuccess) {
                viewModel.getChecklistList()
            }
        }
    }

    private fun setChecklistList(checklistList: ArrayList<Checklist>) {
        val reversedChecklistList = checklistList.reversed() as ArrayList
        val checklistListAdapter = ChecklistListAdapter(reversedChecklistList)
        binding.rvChecklistList.adapter = checklistListAdapter

        checklistListAdapter.setOnItemClickCallback(object :
            ChecklistListAdapter.OnItemClickCallback {
            override fun onClickedItem(data: Checklist) {
                Toast.makeText(this@HomeActivity, data.name, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
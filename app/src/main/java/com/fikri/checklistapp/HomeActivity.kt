package com.fikri.checklistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.core.ui.adapter.ChecklistListAdapter
import com.fikri.checklistapp.databinding.ActivityHomeBinding
import com.fikri.checklistapp.view_model.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    companion object {
        const val CREATE_CHECKLIST_RESULT = 110
        const val CREATE_CHECKLIST_STATUS_RESULT = "create_checklist_status_result"
        const val UPDATE_CHECKLIST_ITEM_RESULT = 100
        const val UPDATE_CHECKLIST_ITEM_STATUS_RESULT = "update_checklist_item_status_result"
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

        viewModel.initialGetChecklistList.observe(this@HomeActivity) {
            it.getContentIfNotHandled()?.let { isInitializing ->
                if (isInitializing) {
                    viewModel.getChecklistList()
                }
            }
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

        viewModel.deleteChecklistResponse.observe(this@HomeActivity) {
            it.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is ApiResultWrapper.Success -> {
                        viewModel.getChecklistList()
                    }
                    is ApiResultWrapper.Error -> {
                        Toast.makeText(this@HomeActivity, response.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    is ApiResultWrapper.NetworkError -> {
                        Toast.makeText(this@HomeActivity, "Connection Failed", Toast.LENGTH_SHORT)
                            .show()
                    }
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
                launcherIntentCreateChecklist.launch(moveCreateChecklist)
            }

            srlHome.apply {
                setColorSchemeColors(
                    ContextCompat.getColor(
                        this@HomeActivity,
                        R.color.secondary_color
                    )
                )
                setOnRefreshListener {
                    isRefreshing = false
                    viewModel.getChecklistList()
                }
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

    private val launcherIntentUpdateChecklistItem = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == UPDATE_CHECKLIST_ITEM_RESULT) {
            val isUpdated = result.data?.getBooleanExtra(UPDATE_CHECKLIST_ITEM_STATUS_RESULT, false)
            if (isUpdated != null && isUpdated) {
                viewModel.getChecklistList()
            }
        }
    }

    private fun setChecklistList(checklistList: ArrayList<Checklist>) {
        var reversedChecklistList = checklistList
        if (checklistList.isNotEmpty() && checklistList.size > 1) {
            reversedChecklistList = checklistList.reversed() as ArrayList<Checklist>
        }
        val checklistListAdapter = ChecklistListAdapter(reversedChecklistList)
        binding.rvChecklistList.adapter = checklistListAdapter

        checklistListAdapter.setOnItemClickCallback(object :
            ChecklistListAdapter.OnItemClickCallback {
            override fun onClickedItem(data: Checklist) {
                val moveChecklistItemList =
                    Intent(this@HomeActivity, ChecklistItemListActivity::class.java)
                moveChecklistItemList.putExtra(
                    ChecklistItemListActivity.EXTRA_TOKEN,
                    viewModel.token
                )
                moveChecklistItemList.putExtra(
                    ChecklistItemListActivity.EXTRA_SELECTED_CHECKLIST,
                    data
                )
                launcherIntentUpdateChecklistItem.launch(moveChecklistItemList)
            }

            override fun onDeleteItem(data: Checklist) {
                viewModel.deleteChecklist(data.id ?: -1)
            }
        })
    }
}
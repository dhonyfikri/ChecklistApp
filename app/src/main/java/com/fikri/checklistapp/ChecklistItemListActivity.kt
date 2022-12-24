package com.fikri.checklistapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.ChecklistItem
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.core.ui.adapter.ChecklistItemListAdapter
import com.fikri.checklistapp.core.ui.modal.AddChecklistItemModal
import com.fikri.checklistapp.databinding.ActivityChecklistItemListBinding
import com.fikri.checklistapp.view_model.ChecklistItemListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChecklistItemListActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SELECTED_CHECKLIST = "extra_selected_checklist"
        const val EXTRA_TOKEN = "extra_token"
    }

    private lateinit var binding: ActivityChecklistItemListBinding
    private val viewModel: ChecklistItemListViewModel by viewModel()
    private val addChecklistItemModal = AddChecklistItemModal(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecklistItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupData()
        setupAction()
    }

    private fun setupData() {

        binding.apply {
            rvChecklistItemList.setHasFixedSize(true)
            rvChecklistItemList.layoutManager =
                LinearLayoutManager(
                    this@ChecklistItemListActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }

        if (intent.getParcelableExtra<Token>(EXTRA_TOKEN) != null) {
            viewModel.token = intent.getParcelableExtra<Token>(EXTRA_TOKEN) as Token
        }
        if (intent.getParcelableExtra<Checklist>(EXTRA_SELECTED_CHECKLIST) != null) {
            viewModel.selectedChecklist =
                intent.getParcelableExtra<Checklist>(EXTRA_SELECTED_CHECKLIST) as Checklist
            title = "Item by ${viewModel.selectedChecklist?.name}"
        }

        viewModel.initialGetChecklistItemList.observe(this@ChecklistItemListActivity) {
            it.getContentIfNotHandled()?.let { isInitializing ->
                if (isInitializing) {
                    viewModel.getChecklistItemList()
                }
            }
        }

        viewModel.checklistItemList.observe(this) {
            when (it) {
                is Resource.Success -> {
                    setChecklistItemList(it.data as ArrayList<ChecklistItem>)
                }
                is Resource.Error -> {
                    Toast.makeText(this@ChecklistItemListActivity, it.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.NetworkError -> {
                    Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // to do something
                }
            }
        }

        viewModel.createChecklistItemResponse.observe(this) {
            when (it) {
                is ApiResultWrapper.Success -> {
                    Toast.makeText(this@ChecklistItemListActivity, it.message, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.getChecklistItemList()
                }
                is ApiResultWrapper.Error -> {
                    Toast.makeText(this@ChecklistItemListActivity, it.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is ApiResultWrapper.NetworkError -> {
                    Toast.makeText(
                        this@ChecklistItemListActivity,
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

        viewModel.showingAddModal.observe(this) {
            if (it) {
                addChecklistItemModal.showAddChecklistItemModal(
                    onCancelClicked = {
                        viewModel.setShowingAddModal(false)
                    },
                    onSaveClicked = { newItemName ->
                        viewModel.setShowingAddModal(false)
                        viewModel.createChecklistItem(newItemName)
                    })
            } else {
                addChecklistItemModal.dismiss()
            }
        }
    }

    private fun setupAction() {
        binding.fabCreateNewChecklistItem.setOnClickListener {
            viewModel.setShowingAddModal(true)
        }
    }

    private fun setChecklistItemList(checklistItemList: ArrayList<ChecklistItem>) {
        checklistItemList.reverse()
        val checklistItemListAdapter = ChecklistItemListAdapter(checklistItemList)
        binding.rvChecklistItemList.adapter = checklistItemListAdapter

        checklistItemListAdapter.setOnItemClickCallback(object :
            ChecklistItemListAdapter.OnItemClickCallback {
            override fun onClickedItem(data: ChecklistItem) {
                TODO("Not yet implemented")
            }

            override fun onDeleteItem(data: ChecklistItem) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.setShowingAddModal(false)
    }
}
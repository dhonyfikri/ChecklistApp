package com.fikri.checklistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fikri.checklistapp.core.data.source.Resource
import com.fikri.checklistapp.core.data.source.remote.response.ApiResultWrapper
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.ChecklistItem
import com.fikri.checklistapp.core.domain.model.Token
import com.fikri.checklistapp.core.ui.adapter.ChecklistItemListAdapter
import com.fikri.checklistapp.core.ui.modal.AddChecklistItemModal
import com.fikri.checklistapp.core.ui.modal.DetailChecklistItemModal
import com.fikri.checklistapp.core.ui.modal.UpdateChecklistItemModal
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
    private val detailModal = DetailChecklistItemModal(this)
    private val updateModal = UpdateChecklistItemModal(this)

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
                    viewModel.getChecklistItemList()
                    viewModel.setUpdatedActivity()
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

        viewModel.deleteChecklistItemResponse.observe(this@ChecklistItemListActivity) {
            it.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is ApiResultWrapper.Success -> {
                        viewModel.getChecklistItemList()
                        viewModel.setUpdatedActivity()
                    }
                    is ApiResultWrapper.Error -> {
                        Toast.makeText(
                            this@ChecklistItemListActivity,
                            response.message,
                            Toast.LENGTH_SHORT
                        )
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
                }
            }
        }

        viewModel.updateChecklistItemResponse.observe(this@ChecklistItemListActivity) {
            it.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is ApiResultWrapper.Success -> {
                        viewModel.getChecklistItemList()
                        viewModel.setUpdatedActivity()
                    }
                    is ApiResultWrapper.Error -> {
                        Toast.makeText(
                            this@ChecklistItemListActivity,
                            response.message,
                            Toast.LENGTH_SHORT
                        )
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
                }
            }
        }

        viewModel.updateStatusChecklistItemResponse.observe(this@ChecklistItemListActivity) {
            it.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is ApiResultWrapper.Success -> {
                        viewModel.getChecklistItemList()
                        viewModel.setUpdatedActivity()
                    }
                    is ApiResultWrapper.Error -> {
                        Toast.makeText(
                            this@ChecklistItemListActivity,
                            response.message,
                            Toast.LENGTH_SHORT
                        )
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
                }
            }
        }

        viewModel.showingAddModal.observe(this) {
            if (it) {
                addChecklistItemModal.showAddChecklistItemModal(
                    viewModel.currentAddChecklistName,
                    onNameChange = { currentName ->
                        viewModel.currentAddChecklistName = currentName
                    },
                    onCancelClicked = {
                        viewModel.setShowingAddModal(false)
                    },
                    onSaveClicked = { newItemName ->
                        viewModel.setShowingAddModal(false)
                        viewModel.createChecklistItem(newItemName)
                    })
            } else {
                addChecklistItemModal.dismiss()
                viewModel.currentAddChecklistName = ""
            }
        }

        viewModel.showingUpdateModal.observe(this) {
            if (it) {
                updateModal.showUpdateChecklistItemModal(
                    viewModel.selectedChecklistItem,
                    viewModel.currentUpdateChecklistName,
                    onNameChange = { currentName ->
                        viewModel.currentUpdateChecklistName = currentName
                    },
                    onCancelClicked = {
                        viewModel.setShowingUpdateModal(false)
                    },
                    onUpdateClicked = { checklistItemId, newItemName ->
                        viewModel.setShowingUpdateModal(false)
                        viewModel.updateChecklistItem(checklistItemId, newItemName)
                    })
            } else {
                updateModal.dismiss()
                viewModel.currentUpdateChecklistName = null
            }
        }

        viewModel.showingDetailModal.observe(this) {
            if (it) {
                detailModal.showDetailChecklistItemModal(
                    viewModel.detailChecklistItem,
                    onCancelClicked = {
                        viewModel.dismissDetailModal()
                    },
                    onUpdateStatusClicked = { checklistItemId ->
                        viewModel.dismissDetailModal()
                        viewModel.updateStatusChecklistItem(checklistItemId)
                    }
                )
            } else {
                detailModal.dismiss()
            }
        }

        viewModel.updatedActivity.observe(this) {
            setActivityResult(it)
        }
    }

    private fun setupAction() {
        binding.apply {
            fabCreateNewChecklistItem.setOnClickListener {
                viewModel.setShowingAddModal(true)
            }

            srlChecklistItem.apply {
                setColorSchemeColors(
                    ContextCompat.getColor(
                        this@ChecklistItemListActivity,
                        R.color.secondary_color
                    )
                )
                setOnRefreshListener {
                    isRefreshing = false
                    viewModel.getChecklistItemList()
                }
            }
        }
    }

    private fun setChecklistItemList(checklistItemList: ArrayList<ChecklistItem>) {
        var reversedChecklistItemList = checklistItemList
        if (checklistItemList.isNotEmpty() && checklistItemList.size > 1) {
            reversedChecklistItemList = checklistItemList.reversed() as ArrayList<ChecklistItem>
        }
        val checklistItemListAdapter = ChecklistItemListAdapter(reversedChecklistItemList)
        binding.rvChecklistItemList.adapter = checklistItemListAdapter

        checklistItemListAdapter.setOnItemClickCallback(object :
            ChecklistItemListAdapter.OnItemClickCallback {
            override fun onClickedItem(data: ChecklistItem) {
                viewModel.getDetailChecklistItem(data.id ?: -1)
            }

            override fun onUpdateItem(data: ChecklistItem) {
                viewModel.selectedChecklistItem = data
                viewModel.setShowingUpdateModal(true)
            }

            override fun onDeleteItem(data: ChecklistItem) {
                viewModel.deleteChecklistItem(data.id ?: -1)
            }

        })
    }

    private fun setActivityResult(isUpdated: Boolean) {
        val intent = Intent()
        intent.putExtra(HomeActivity.UPDATE_CHECKLIST_ITEM_STATUS_RESULT, isUpdated)
        setResult(HomeActivity.UPDATE_CHECKLIST_ITEM_RESULT, intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        addChecklistItemModal.dismiss()
        detailModal.dismiss()
        updateModal.dismiss()
    }
}
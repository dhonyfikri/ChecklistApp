package com.fikri.checklistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.core.domain.model.Token
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
        }
    }

    private fun setupAction() {

    }
}
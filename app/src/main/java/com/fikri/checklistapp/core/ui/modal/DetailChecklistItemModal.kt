package com.fikri.checklistapp.core.ui.modal

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.fikri.checklistapp.R
import com.fikri.checklistapp.core.domain.model.ChecklistItem

class DetailChecklistItemModal(private val context: Context) : MyModal() {

    fun showAddChecklistItemModal(
        checklistItem: ChecklistItem?,
        onCancelClicked: (() -> Unit)? = null,
        onUpdateStatusClicked: ((checklistItemId: Int) -> Unit)? = null
    ) {
        dismiss()
        modal = Dialog(context)
        modal?.setContentView(R.layout.detail_checklist_item_modal)
        val tvChecklistItemName = modal?.findViewById<TextView>(R.id.tv_checklist_item_name)
        val btnCancel = modal?.findViewById<Button>(R.id.btn_cancel)
        val btnUpdateStatus = modal?.findViewById<Button>(R.id.btn_update_status)

        tvChecklistItemName?.text = checklistItem?.name
        btnUpdateStatus?.text =
            context.getString(
                if (checklistItem?.itemCompletionStatus == true) {
                    R.string.set_uncompleted
                } else {
                    R.string.set_completed
                }
            )

        btnCancel?.setOnClickListener {
            onCancelClicked?.invoke()
        }
        btnUpdateStatus?.setOnClickListener {
            onUpdateStatusClicked?.invoke(checklistItem?.id ?: -1)
        }

        showModal()
    }
}
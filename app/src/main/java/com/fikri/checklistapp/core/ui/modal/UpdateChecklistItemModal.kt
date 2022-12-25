package com.fikri.checklistapp.core.ui.modal

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.fikri.checklistapp.R
import com.fikri.checklistapp.core.domain.model.ChecklistItem

class UpdateChecklistItemModal(private val context: Context) : MyModal() {

    fun showUpdateChecklistItemModal(
        checklistItem: ChecklistItem? = null,
        initialNameValue: String? = null,
        onNameChange: ((itemName: String) -> Unit)? = null,
        onCancelClicked: (() -> Unit)? = null,
        onUpdateClicked: ((checklistItemId: Int, itemName: String) -> Unit)? = null
    ) {
        dismiss()
        modal = Dialog(context)
        modal?.setContentView(R.layout.update_checklist_item_modal)
        val etUpdateItemName = modal?.findViewById<EditText>(R.id.et_update_item_name)
        val btnCancel = modal?.findViewById<Button>(R.id.btn_cancel)
        val btnUpdate = modal?.findViewById<Button>(R.id.btn_update)

        etUpdateItemName?.setText(checklistItem?.name)

        initialNameValue?.let {
            etUpdateItemName?.setText(it)
        }

        etUpdateItemName?.addTextChangedListener(onTextChanged = { p0, _, _, _ ->
            onNameChange?.invoke(p0.toString())
        })

        btnCancel?.setOnClickListener {
            onCancelClicked?.invoke()
        }

        btnUpdate?.setOnClickListener {
            onUpdateClicked?.invoke(
                checklistItem?.id ?: -1,
                etUpdateItemName?.text.toString().trim()
            )
        }

        showModal()
    }
}
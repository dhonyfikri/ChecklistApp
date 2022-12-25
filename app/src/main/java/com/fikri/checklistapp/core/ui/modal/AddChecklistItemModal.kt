package com.fikri.checklistapp.core.ui.modal

import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.fikri.checklistapp.R

class AddChecklistItemModal(private val context: Context) : MyModal() {

    fun showAddChecklistItemModal(
        initialNameValue: String,
        onNameChange: ((itemName: String) -> Unit)? = null,
        onCancelClicked: (() -> Unit)? = null,
        onSaveClicked: ((itemName: String) -> Unit)? = null
    ) {
        dismiss()
        modal = Dialog(context)
        modal?.setContentView(R.layout.create_new_checklist_item_modal)
        val etNewItem = modal?.findViewById<EditText>(R.id.et_new_item)
        val btnCancel = modal?.findViewById<Button>(R.id.btn_cancel)
        val btnSave = modal?.findViewById<Button>(R.id.btn_save)

        etNewItem?.setText(initialNameValue)

        etNewItem?.addTextChangedListener(onTextChanged = { p0, _, _, _ ->
            onNameChange?.invoke(p0.toString())
        })

        btnCancel?.setOnClickListener {
            onCancelClicked?.invoke()
        }

        btnSave?.setOnClickListener {
            onSaveClicked?.invoke(etNewItem?.text.toString().trim())
        }

        showModal()
    }
}
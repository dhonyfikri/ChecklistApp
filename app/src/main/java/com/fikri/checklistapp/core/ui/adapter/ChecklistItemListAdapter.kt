package com.fikri.checklistapp.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fikri.checklistapp.R
import com.fikri.checklistapp.core.domain.model.ChecklistItem
import com.fikri.checklistapp.databinding.ChecklistItemBinding

class ChecklistItemListAdapter(private val listChecklistItem: ArrayList<ChecklistItem>) :
    RecyclerView.Adapter<ChecklistItemListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(var binding: ChecklistItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ChecklistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val checklistItem = listChecklistItem[position]
        holder.apply {
            binding.apply {
                tvChecklistName.text = checklistItem.name
                btnToggleChecklistStatus.setImageDrawable(
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        if (checklistItem.itemCompletionStatus == true) R.drawable.ic_check else R.drawable.ic_close
                    )
                )
            }
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onClickedItem(checklistItem)
        }

        holder.binding.btnDeleteChecklist.setOnClickListener {
            onItemClickCallback.onDeleteItem(checklistItem)
        }
    }

    override fun getItemCount() = listChecklistItem.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onClickedItem(data: ChecklistItem)
        fun onDeleteItem(data: ChecklistItem)
    }
}
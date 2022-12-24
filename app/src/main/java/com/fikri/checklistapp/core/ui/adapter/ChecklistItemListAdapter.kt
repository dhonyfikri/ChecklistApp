package com.fikri.checklistapp.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
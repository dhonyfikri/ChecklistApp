package com.fikri.checklistapp.core.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fikri.checklistapp.R
import com.fikri.checklistapp.core.domain.model.Checklist
import com.fikri.checklistapp.databinding.ChecklistItemBinding

class ChecklistListAdapter(private val listChecklist: ArrayList<Checklist>) :
    RecyclerView.Adapter<ChecklistListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(var binding: ChecklistItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ChecklistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val checklist = listChecklist[position]

        holder.apply {
            binding.apply {
                btnEditChecklistItem.visibility = View.GONE
                btnToggleChecklistStatus.setImageDrawable(
                    ContextCompat.getDrawable(
                        holder.itemView.context,
                        if (checklist.checklistCompletionStatus == true) R.drawable.ic_check else R.drawable.ic_close
                    )
                )
                tvChecklistName.text = checklist.name
            }
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onClickedItem(checklist)
        }

        holder.binding.btnDeleteChecklist.setOnClickListener {
            onItemClickCallback.onDeleteItem(checklist)
        }
    }

    override fun getItemCount() = listChecklist.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onClickedItem(data: Checklist)
        fun onDeleteItem(data: Checklist)
    }
}
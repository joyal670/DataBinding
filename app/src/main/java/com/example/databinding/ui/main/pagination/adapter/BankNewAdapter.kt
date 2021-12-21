package com.example.databinding.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.databinding.ItemBankBinding
import com.example.databinding.model.view_bank_details.AgentBankDetail
import com.example.databinding.ui.main.pagination.viewmodel.BankNewViewModel


class BankNewAdapter(private val viewModel: BankNewViewModel) :
    PagingDataAdapter<AgentBankDetail, BankNewAdapter.PostViewHolder>(PostComparator()) {

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(this.viewModel, item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    class PostViewHolder(private val binding: ItemBankBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: BankNewViewModel, item: AgentBankDetail) {
            binding.itemBank = item
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBankBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(binding)
            }
        }
    }
}

class PostComparator : DiffUtil.ItemCallback<AgentBankDetail>() {
    override fun areItemsTheSame(oldItem: AgentBankDetail, newItem: AgentBankDetail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AgentBankDetail, newItem: AgentBankDetail): Boolean {
        return oldItem == newItem
    }

}
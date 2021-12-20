package com.example.databinding.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.R
import com.example.databinding.databinding.ItemBankBinding
import com.example.databinding.databinding.ItemLoaderBinding
import com.example.databinding.model.view_bank_details.AgentBankDetail


class BankAdapter(private val bankList: ArrayList<AgentBankDetail>,  private var selectedItemId: (Int) -> Unit, private val mListener: ProductItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_DATA = 0
        private const val VIEW_TYPE_PROGRESS = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_DATA -> {
                ViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_bank, parent, false))
            }
            VIEW_TYPE_PROGRESS -> {
                ViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_loader, parent, false))
            }
            else -> throw IllegalArgumentException("Different View type")
        }
    }

    override fun getItemCount(): Int = bankList.size

    override fun getItemViewType(position: Int): Int {
        return when (bankList[position].id) {
            -1 -> BankAdapter.VIEW_TYPE_PROGRESS
            else -> BankAdapter.VIEW_TYPE_DATA
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(bankList[position])
        }
        if (holder is ProgressHolder) {

        }
    }

    inner class ViewHolder(private val binding: ItemBankBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AgentBankDetail) {
            binding.itemBank = item
            binding.itemClick = mListener
            binding.executePendingBindings()
            /* method one */
           /* binding.materialCard.setOnClickListener {
                selectedItemId.invoke(bankList[position].id )
            }*/
        }
    }

    inner class ProgressHolder(private val binding: ItemLoaderBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface ProductItemClickListener {
        fun onProductItemClicked(item: AgentBankDetail)
    }

}












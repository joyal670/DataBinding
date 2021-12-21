package com.example.databinding.ui.main.pagination.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.databinding.ui.main.pagination.viewmodel.BankNewViewModel

class ViewModelFactory(private val repository: BankRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BankNewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BankNewViewModel(repository) as T
        }
        throw IllegalArgumentException("Exception: Unknown ViewModel class")
    }
}
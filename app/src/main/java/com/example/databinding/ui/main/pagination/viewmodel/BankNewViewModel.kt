package com.example.databinding.ui.main.pagination.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.databinding.model.view_bank_details.AgentBankDetail
import com.example.databinding.ui.main.pagination.data.BankPageSource
import com.example.databinding.ui.main.pagination.data.BankRepository
import kotlinx.coroutines.flow.Flow

class BankNewViewModel(private var repository  : BankRepository)  :ViewModel() {

    val posts: Flow<PagingData<AgentBankDetail>> = selectPosts()

    private fun selectPosts(): Flow<PagingData<AgentBankDetail>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { BankPageSource(repository) }
        ).flow.cachedIn(viewModelScope)
    }

    companion object {
        const val PAGE_SIZE = 10
    }

}
package com.example.databinding.ui.main.pagination.data

import com.example.databinding.model.view_bank_details.AgentViewBankDetailsResponse
import com.example.databinding.ui.main.pagination.data.BankDataSource
import retrofit2.Response

class BankRepository(private var dataSource: BankDataSource) {

    suspend fun viewBankDetails(page: Int): Response<AgentViewBankDetailsResponse> =
        dataSource.viewBankDetails(page)
}
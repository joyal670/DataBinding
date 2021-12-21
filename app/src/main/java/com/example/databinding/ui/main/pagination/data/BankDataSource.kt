package com.example.databinding.ui.main.pagination.data

import com.example.databinding.data.ApiService
import com.example.databinding.model.view_bank_details.AgentViewBankDetailsResponse
import retrofit2.Response

class BankDataSource(private var apiService: ApiService) {

    suspend fun viewBankDetails(page: Int): Response<AgentViewBankDetailsResponse> =
        apiService.viewBankDetails1(page)
}
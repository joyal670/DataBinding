package com.ncomfortsagent.model.view_bank_details

import com.example.databinding.model.view_bank_details.AgentBankDetail

data class AgentViewBankDetailsData(
    val agent_bank_details: List<AgentBankDetail>,
    val current_page: String,
    val total_page_count: Int
)
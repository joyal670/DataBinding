package com.example.databinding.model.login

data class AgentLoginResponse(
    val `data`: AgentLoginData,
    val response: String,
    val status: Int
)
package com.example.databinding.data

import com.example.databinding.model.login.AgentLoginResponse
import com.example.databinding.utlis.AppPreferences.prefUserToken
import com.example.databinding.model.view_bank_details.AgentViewBankDetailsResponse
import retrofit2.Response

class MainRepository(private val apiHelper: ApiService) {

    /* Login Api */
    suspend fun login(phone: String, password: String): Response<AgentLoginResponse> =
        apiHelper.login(phone, password)

    fun saveToken(userToken  :String){
        prefUserToken = userToken
    }

    /* View Bank Details Api */
    suspend fun viewBankDetails(page: String): Response<AgentViewBankDetailsResponse> =
        apiHelper.viewBankDetails(page)
}
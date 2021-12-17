package com.example.databinding.data

import com.example.databinding.model.login.AgentLoginResponse
import com.example.databinding.model.profile.AgentProfileResponse
import com.example.databinding.utlis.AppPreferences.prefUserToken
import retrofit2.Response

class MainRepository(private val apiHelper: ApiService) {

    /* Login Api */
    suspend fun login(phone: String, password: String): Response<AgentLoginResponse> =
        apiHelper.login(phone, password)

    fun saveToken(userToken  :String){
        prefUserToken = userToken
    }

}
package com.example.databinding.ui

import android.app.Activity
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databinding.data.ApiRepositoryProvider
import com.example.databinding.model.login.AgentLoginResponse
import com.example.databinding.utlis.Constants
import com.example.databinding.utlis.PasswordError
import com.example.databinding.utlis.Resource
import com.example.databinding.utlis.USERNAME_ERROR
import kotlinx.coroutines.launch

class LoginViewModel(var context: Activity) : ViewModel() {

    private val liveDataLogin = MutableLiveData<Resource<AgentLoginResponse>>()

    val loginLiveData: LiveData<Resource<AgentLoginResponse>> get() = liveDataLogin

    var userEmail: MutableLiveData<String>? = null
    var userPassword: MutableLiveData<String>? = null

    var responseMsg: MutableLiveData<String>? = null
    var errorEmail: MutableLiveData<String>? = null
    var errorPassword: MutableLiveData<String>? = null

    var responseUserName: MutableLiveData<String>? = null
    var responseEmailAddress: MutableLiveData<String>? = null


    init {
        userPassword = MutableLiveData()
        userEmail = MutableLiveData()
        responseMsg = MutableLiveData()
        errorEmail = MutableLiveData()
        errorPassword = MutableLiveData()
        responseUserName = MutableLiveData()
        responseEmailAddress = MutableLiveData()

        responseMsg?.value = ""
    }

    fun onUserNameTextChanged(text: CharSequence) {
        responseMsg?.value = ""
        if (!TextUtils.isEmpty(text.toString())) {
            userEmail?.value = text.toString()
            errorEmail?.value = null
        } else {
            userEmail?.value = null
        }
    }

    fun onPasswordTextChanged(text: CharSequence) {
        responseMsg?.value = ""
        if (!TextUtils.isEmpty(text.toString())) {
            userPassword?.value = text.toString()
            errorPassword?.value = null
        } else {
            userPassword?.value = null
        }
    }


    private fun isValid(): Boolean {
        return when {
            TextUtils.isEmpty(userEmail?.value) -> {
                errorEmail?.value = USERNAME_ERROR
                false
            }
            TextUtils.isEmpty(userPassword?.value) -> {
                errorPassword?.value = PasswordError
                false
            }
            else -> {
                true
            }
        }
    }


    fun login() {
        if (isValid()) {
            val repository = ApiRepositoryProvider.providerApiRepository()
            viewModelScope.launch {
                try {
                    liveDataLogin.postValue(Resource.loading(null))
                    repository.login(
                        userEmail?.value.toString(),
                        userPassword?.value.toString()
                    ).let {
                        val response = it.body()
                        when (response!!.status) {
                            Constants.REQUEST_OK -> {
                                liveDataLogin.postValue(Resource.success(response))

                                responseMsg?.value = "Success"
                                responseUserName?.value = response.data.name
                                responseEmailAddress?.value = response.data.email

                                repository.saveToken(response.data.api_token)
                            }
                            else -> {
                                liveDataLogin.postValue(Resource.error(response.response, response))
                                responseMsg?.value = response.response
                                Toast.makeText(
                                    context,
                                    "" + responseMsg?.value.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()

                                responseEmailAddress?.value = ""
                                responseUserName?.value = ""
                            }
                        }
                    }
                } catch (ex: Exception) {
                    liveDataLogin.postValue(Resource.noInternet("", null))
                }
            }
        }
    }
}
package com.example.databinding.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databinding.data.ApiRepositoryProvider
import com.example.databinding.model.view_bank_details.AgentViewBankDetailsResponse
import com.example.databinding.utlis.Constants
import com.example.databinding.utlis.Resource
import kotlinx.coroutines.launch

class BankViewModel : ViewModel() {

    private val liveDataBankDetails = MutableLiveData<Resource<AgentViewBankDetailsResponse>>()

    val bankLiveData: LiveData<Resource<AgentViewBankDetailsResponse>> get() = liveDataBankDetails

    fun getBankDetails(page: String) {
        val repository = ApiRepositoryProvider.providerApiRepository()
        viewModelScope.launch {
            try {
                liveDataBankDetails.postValue(Resource.loading(null))
                repository.viewBankDetails(page).let {
                    val response = it.body()
                    when (response!!.status) {
                        Constants.REQUEST_OK -> {
                            liveDataBankDetails.postValue(Resource.success(response))

                        }
                        else -> {
                            liveDataBankDetails.postValue(Resource.error("some", response))

                        }
                    }
                }
            } catch (ex: Exception) {
                liveDataBankDetails.postValue(Resource.noInternet("went", null))
            }
        }
    }
}
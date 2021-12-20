package com.example.databinding.ui.main.activity


import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding.R
import com.example.databinding.base.BaseActivity
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.ui.main.adapter.BankAdapter
import com.example.databinding.ui.main.viewmodel.BankViewModel
import com.example.databinding.utlis.Status
import com.example.databinding.model.view_bank_details.AgentBankDetail

class MainActivity : BaseActivity<ActivityMainBinding, BankViewModel>(), BankAdapter.ProductItemClickListener
{
    var bankList = ArrayList<AgentBankDetail>()

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): Class<BankViewModel> = BankViewModel::class.java


    override fun initData() {
        setOnClicks()
        viewModel.getBankDetails("1")

        viewModel.bankLiveData.observe(this,{
            when(it.status){
                Status.SUCCESS -> {
                    bankList.addAll(it.data!!.data.agent_bank_details)

                    binding.rvBank.layoutManager = LinearLayoutManager(this)
                    binding.rvBank.adapter = BankAdapter(bankList, {selectedItemId(it)},this)
                }
                Status.ERROR -> {

                }
                Status.NO_INTERNET -> {

                }
                Status.LOADING -> {

                }
            }
        } )

    }

    private fun selectedItemId(it: Int) {
        Log.e("TAG", "selectedItemId: $it" )
    }

    private fun setOnClicks() {


    }

    override fun onProductItemClicked(item: AgentBankDetail) {
        Log.e("TAG", "onProductItemClicked: " +item.id )
    }


}
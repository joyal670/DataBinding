package com.example.databinding.ui


import android.util.Log
import com.example.databinding.R
import com.example.databinding.base.BaseActivity
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.utlis.AppPreferences.prefUserToken

class MainActivity : BaseActivity<ActivityMainBinding, LoginViewModel>() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java


    override fun initData() {
        setOnTextChanged()
        setOnClicks()

        binding.loginViewModel = LoginViewModel(this)

       /* binding.loginViewModel?.loginLiveData?.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(this, "" + it.data!!.response, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    // Toast.makeText(this, ""+it.data!!.response, Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, "" + it.data!!.response, Toast.LENGTH_SHORT).show()
                }
                Status.NO_INTERNET -> {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
            }
        })*/
    }

    private fun setOnTextChanged() {

        /* binding.editUserName.doOnTextChanged { text, start, before, count ->
             viewModel.onUserNameTextChanged(text.toString())
         }

         binding.editPassword.doOnTextChanged { text, start, before, count ->
             viewModel.onPasswordTextChanged(text.toString())
         }*/
    }


    private fun setOnClicks() {

        /*binding.btnLogin.setOnClickListener {
            viewModel.login()
        }*/
    }


}
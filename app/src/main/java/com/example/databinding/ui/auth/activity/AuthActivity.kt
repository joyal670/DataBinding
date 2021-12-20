package com.example.databinding.ui.auth.activity

import com.example.databinding.R
import com.example.databinding.base.BaseActivity
import com.example.databinding.databinding.ActivityAuthBinding
import com.example.databinding.ui.auth.fragment.LoginFragment
import com.example.databinding.ui.auth.viewmodel.EmptyViewModel
import com.example.databinding.utlis.replaceFragment

class AuthActivity : BaseActivity<ActivityAuthBinding, EmptyViewModel>() {

    override fun getLayoutRes(): Int = R.layout.activity_auth

    override fun getViewModel(): Class<EmptyViewModel> = EmptyViewModel::class.java

    override fun initData() {

        replaceFragment(fragment = LoginFragment())
    }

}
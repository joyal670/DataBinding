package com.example.databinding.ui.auth.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.databinding.base.BaseFragment
import com.example.databinding.databinding.FragmentLoginBinding
import com.example.databinding.ui.auth.viewmodel.LoginViewModel


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater, container, false)
    }

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun initViews() {
        binding.loginViewModel = LoginViewModel(requireActivity())

        /*  viewModel.loginLiveData.observe(viewLifecycleOwner, {
              Log.e("TAG", "initViews: $it" )
          })*/



    }





}
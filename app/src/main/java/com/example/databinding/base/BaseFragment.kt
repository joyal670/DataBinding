package com.example.databinding.base

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

abstract class BaseFragment< DB : ViewDataBinding, VM : ViewModel> : Fragment() {


    protected lateinit var binding: DB
    //protected lateinit var viewModel: VM
    abstract fun initViews()

    val viewModel by lazy {
        ViewModelProvider(this).get(getViewModel())
    }

    abstract fun getViewModel(): Class<VM>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = getFragmentBinding(inflater, container)
        binding.lifecycleOwner = this
        //viewModel = ViewModelProvider(this).get(getViewModel())
        initViews()
        return binding.root
    }


    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): DB



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    open fun getLoadingWidth(): Int {
        val metrics = this.resources.displayMetrics
        return metrics.widthPixels
    }

    open fun getLoadingHeight(): Int {
        val metrics = this.resources.displayMetrics
        return metrics.heightPixels
    }


}
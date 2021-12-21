package com.example.databinding.ui.main.pagination.activity


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding.R
import com.example.databinding.data.ApiService
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.model.view_bank_details.AgentBankDetail
import com.example.databinding.ui.main.*
import com.example.databinding.ui.main.pagination.data.BankDataSource
import com.example.databinding.ui.main.pagination.data.BankRepository
import com.example.databinding.ui.main.pagination.data.ViewModelFactory
import com.example.databinding.ui.main.pagination.adapter.CustomLoadStateAdapter
import com.example.databinding.ui.main.pagination.viewmodel.BankNewViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainActivity1 : AppCompatActivity() {
    var bankList = ArrayList<AgentBankDetail>()
   /* private lateinit var bankAdapter1: BankAdapter1
    private lateinit var bankViewModel1: BankViewModel1*/


    private lateinit var viewModel: BankNewViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BankNewAdapter
    private var selectPosts: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

       /* bankViewModel1 = ViewModelProvider(this, MainViewModelFactory(ApiService.create()))[BankViewModel1::class.java]

        bankAdapter1 = BankAdapter1()
        binding.rvBank.layoutManager = LinearLayoutManager(this)
        bankAdapter1 = BankAdapter1()
        binding.rvBank.adapter = bankAdapter1
        lifecycleScope.launch {
            bankViewModel1.listData.collect {
                bankAdapter1.submitData(it)
            }

        }*/


        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(BankRepository(BankDataSource(ApiService.create())))
        )
            .get(BankNewViewModel::class.java)


        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = BankNewAdapter(viewModel = viewModel)
        binding.rvBank.layoutManager = LinearLayoutManager(this)
        binding.rvBank.adapter = adapter

        selectPosts?.cancel()
        selectPosts = lifecycleScope.launch {
            viewModel.posts.collectLatest {
                adapter.submitData(it)
            }
        }

         adapter.addLoadStateListener {
            when (it.refresh){
               is LoadState.Loading -> {
                   Log.e("TAG", "setupRecyclerView: Loading" )
                   binding.progressLoading.visibility = View.VISIBLE
                }

               is LoadState.Error -> {
                   Log.e("TAG", "setupRecyclerView: Error" )
                   binding.progressLoading.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    Log.e("TAG", "setupRecyclerView: NotLoading" )
                    binding.progressLoading.visibility = View.GONE
                }
            }

        }

        binding.rvBank.adapter = adapter.withLoadStateFooter(
            CustomLoadStateAdapter{
                adapter.refresh()
            }
        )



        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            adapter.refresh()
        }
    }


}
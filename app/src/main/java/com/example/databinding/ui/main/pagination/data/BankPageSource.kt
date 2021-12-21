package com.example.databinding.ui.main.pagination.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.databinding.model.view_bank_details.AgentBankDetail
import com.example.databinding.model.view_bank_details.AgentViewBankDetailsResponse
import retrofit2.HttpException
import java.io.IOException


const val POST_STARTING_PAGE_INDEX = 1

class BankPageSource(private var bankRepository: BankRepository) : PagingSource<Int, AgentBankDetail>() {
    override fun getRefreshKey(state: PagingState<Int, AgentBankDetail>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AgentBankDetail> {
        return try {
            val position = params.key ?: POST_STARTING_PAGE_INDEX
            val response = bankRepository.viewBankDetails(page = position)
            val data = response.body()!!.data.agent_bank_details
            val prevKey = if (position == POST_STARTING_PAGE_INDEX) null else position - 1
           // val nextKey = if (response.isEmpty()) null else position + 1
            val nextKey = if (response.body()!!.data.agent_bank_details.isEmpty()) null else position + 1
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}
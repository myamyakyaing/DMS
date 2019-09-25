package com.tat.dms.repositories

import android.content.Context
import com.tat.dms.network.ApiService
import com.tat.dms.network.CustomerNetworkResponse
import com.tat.dms.network.SaleItemNetworkResponse
import io.reactivex.Observable

class MainRepositoryImpl(
    private val context: Context,
    private val mApiService: ApiService
) : MainRepository {
    override fun CustomerListData(param_data: String): Observable<CustomerNetworkResponse> {
         return mApiService.loadCustomerList(data = param_data)
    }
}
package com.tat.dms.repositories

import android.content.Context
import com.tat.dms.network.ApiService
import com.tat.dms.network.SaleItemNetworkResponse
import io.reactivex.Observable

class SaleItemRepositoryImp(
    private val context: Context,
    private val mApiService: ApiService
) : SaleItemRepository {
    override fun SaleItemListData(param_data: String): Observable<SaleItemNetworkResponse> {
        return mApiService.loadSaleItemList(data = param_data)
    }

}
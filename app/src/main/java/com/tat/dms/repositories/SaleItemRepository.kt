package com.tat.dms.repositories

import com.tat.dms.network.SaleItemNetworkResponse
import io.reactivex.Observable

interface SaleItemRepository {
    fun SaleItemListData(param_data:String): Observable<SaleItemNetworkResponse>
}
package com.tat.dms.repositories

import com.tat.dms.network.CustomerNetworkResponse
import com.tat.dms.network.SaleItemNetworkResponse
import io.reactivex.Observable

interface MainRepository {
    fun CustomerListData(param_data:String): Observable<CustomerNetworkResponse>
}
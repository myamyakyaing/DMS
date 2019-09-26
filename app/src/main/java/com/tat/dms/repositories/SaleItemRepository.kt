package com.tat.dms.repositories

import androidx.lifecycle.MutableLiveData
import com.tat.dms.network.network_response.SaleItemNetworkResponse
import com.tat.dms.vos.Product
import io.reactivex.Observable

interface SaleItemRepository {
    var productData: MutableLiveData<Observable<SaleItemNetworkResponse>>
    fun SaleItemListData(param_data:String)
    //fun SaleItemListData(param_data:String): Observable<SaleItemNetworkResponse>
    fun saveDataIntoDatabase(productList:List<Product>)
}
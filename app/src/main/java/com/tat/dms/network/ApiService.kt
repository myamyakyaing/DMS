package com.tat.dms.network

import com.tat.dms.network.network_response.CustomerNetworkResponse
import com.tat.dms.network.network_response.SaleItemNetworkResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("v1/customer")
    fun loadCustomerList(@Field ("param_data") data:String): Observable<CustomerNetworkResponse>
    @FormUrlEncoded
    @POST("v1/saleitem")
    fun loadSaleItemList(@Field ("param_data") data:String): Observable<SaleItemNetworkResponse>
}
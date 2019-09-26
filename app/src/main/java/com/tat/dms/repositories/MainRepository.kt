package com.tat.dms.repositories

import androidx.lifecycle.MutableLiveData
import com.tat.dms.network.network_response.CustomerNetworkResponse
import com.tat.dms.vos.Customer
import io.reactivex.Observable

interface MainRepository {
    var customerData: MutableLiveData<Observable<CustomerNetworkResponse>>
    fun CustomerListData(param_data:String)
    //fun CustomerListData(param_data:String): Observable<CustomerNetworkResponse>
    fun saveDataIntoDatabase(customerList:List<Customer>)
}
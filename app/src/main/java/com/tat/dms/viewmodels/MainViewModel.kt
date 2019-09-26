package com.tat.dms.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.tat.dms.repositories.MainRepository
import com.tat.dms.vos.Customer
import com.tat.dms.vos.CustomerData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val mainRepo: MainRepository
) : BaseViewModel() {
    var errorState = MutableLiveData<String>()
    var successState = MutableLiveData<List<Customer>>()
    var customer_data = CustomerData(
        "[]", "2019-08-25",
        "YWNlcGx1cw==",
        4,
        "1234567",
        "1234567",
        "T1"
    )
    var gson = Gson()
    var param_data = gson.toJson(customer_data)

    fun loadCustomerList() {
        mainRepo.customerData
            .observeForever {
                launch {
                    it
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext {
                            mainRepo.saveDataIntoDatabase(it.data[0].customer)
                        }
                        .subscribe({ response ->
                            mainRepo.customerData = MutableLiveData()
                            successState.postValue(response.data[0].customer)
                        }, { error ->
                            errorState.postValue(error.localizedMessage)
                        })
                }
            }


        mainRepo.CustomerListData(param_data)
    }



}

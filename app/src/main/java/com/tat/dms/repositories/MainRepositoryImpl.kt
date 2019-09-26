package com.tat.dms.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tat.dms.db.MyDatabase
import com.tat.dms.network.ApiService
import com.tat.dms.network.network_response.CustomerNetworkResponse
import com.tat.dms.util.Utils
import com.tat.dms.vos.Customer
import com.tat.dms.vos.Data
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainRepositoryImpl(
    private val context: Context,
    private val mApiService: ApiService,
    private val database: MyDatabase
) : MainRepository {
    override var customerData: MutableLiveData<Observable<CustomerNetworkResponse>> =
        MutableLiveData()

    override fun CustomerListData(param_data: String) {
        //override fun CustomerListData(param_data: String): Observable<CustomerNetworkResponse> {

            if (Utils.isOnline(context)) {
                customerData.postValue(mApiService.loadCustomerList(data = param_data))
            } else {
                val localCustomerDataList = database.customerDao().allCustomerData
                val disposable = CompositeDisposable()
                disposable.add(
                    localCustomerDataList
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe {
                            disposable.clear()
                            val customerList: List<Data> = listOf(Data(it))
                            val responseCustomerData =
                                CustomerNetworkResponse()
                            responseCustomerData.data = customerList
                            customerData.postValue(Observable.just(responseCustomerData))
                        }
                )
            }

        }

        override fun saveDataIntoDatabase(customerList: List<Customer>) {
            Observable.fromCallable { database.customerDao().insertAll(customerList) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe()
        }
    }
package com.tat.dms.repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tat.dms.db.MyDatabase
import com.tat.dms.network.ApiService
import com.tat.dms.network.network_response.SaleItemNetworkResponse
import com.tat.dms.util.Utils
import com.tat.dms.vos.Datum
import com.tat.dms.vos.Product
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SaleItemRepositoryImp(
    private val context: Context,
    private val mApiService: ApiService,
    private val database: MyDatabase
) : SaleItemRepository {
    override var productData: MutableLiveData<Observable<SaleItemNetworkResponse>> =
        MutableLiveData()

    //      override fun SaleItemListData(param_data: String): Observable<SaleItemNetworkResponse> {
    //      return mApiService.loadSaleItemList(data = param_data)
    override fun SaleItemListData(param_data: String) {
        if (Utils.isOnline(context)) {
            productData.postValue(mApiService.loadSaleItemList(data = param_data))
        } else {
            val localProductDataList = database.productDao().allProductData
            val disposable = CompositeDisposable()
            disposable.add(
                localProductDataList
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe {
                        disposable.clear()
                        val productList: List<Datum> = listOf(Datum(it))
                        val responseProductData =
                            SaleItemNetworkResponse()
                        responseProductData.data = productList
                        productData.postValue(Observable.just(responseProductData))
                    }
            )
        }

    }

    override fun saveDataIntoDatabase(productList: List<Product>) {
        Observable.fromCallable { database.productDao().allInsert(productList) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }

}
package com.tat.dms.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tat.dms.repositories.SaleRepotrRepository
import com.tat.dms.vos.ReportSaleInvoiceVO
import io.reactivex.android.schedulers.AndroidSchedulers

class SaleReportViewModel(private val repo:SaleRepotrRepository):BaseViewModel() {
    var errorState = MutableLiveData<String>()
    var successState = MutableLiveData<List<ReportSaleInvoiceVO>>()

    fun getSaleInvoiceReport(id:String){

        launch {
            repo.allSaleInvoiceReport(id = id)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    successState.postValue(it)
                },{
                    errorState.value = it.localizedMessage
                })
        }

    }
}
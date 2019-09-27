package com.tat.dms.viewmodels

import androidx.lifecycle.MutableLiveData
import com.tat.dms.repositories.RepotrRepository
import com.tat.dms.vos.ReportInvoiceVO
import io.reactivex.android.schedulers.AndroidSchedulers

class ReportViewModel(private val repo:RepotrRepository):BaseViewModel() {
    var errorState = MutableLiveData<String>()
    var successState = MutableLiveData<List<ReportInvoiceVO>>()

    fun getInvoiceReport(){

        launch {
            repo.allInvoiceReport()
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
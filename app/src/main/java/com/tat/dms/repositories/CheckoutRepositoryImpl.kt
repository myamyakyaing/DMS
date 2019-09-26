package com.tat.dms.repositories

import android.content.Context
import com.tat.dms.db.MyDatabase
import com.tat.dms.network.ApiService
import com.tat.dms.vos.InvoiceVO
import com.tat.dms.vos.SaleInvoiceVO
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CheckoutRepositoryImpl(
    private val context: Context,
    private val mApiService: ApiService,
    private val database:MyDatabase
) : CheckoutRepository {
    override fun saveDataIntoDatabase(
        invoice: InvoiceVO,
        checkoutItem: MutableList<SaleInvoiceVO>
    ) {
        Observable.fromCallable { database.checkoutDao().insertInvoice(invoice) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()

        Observable.fromCallable { database.checkoutDao().insertItemsAll(checkoutItem) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }
}
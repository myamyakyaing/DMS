package com.tat.dms.repositories

import com.tat.dms.vos.ReportSaleInvoiceVO
import io.reactivex.Observable

interface SaleRepotrRepository {
    fun allSaleInvoiceReport(id:String): Observable<List<ReportSaleInvoiceVO>>
}
package com.tat.dms.repositories

import com.tat.dms.db.MyDatabase
import com.tat.dms.vos.ReportSaleInvoiceVO
import io.reactivex.Observable

class SaleRepotrRepositoryImpl(private val database:MyDatabase):SaleRepotrRepository {
    override fun allSaleInvoiceReport(id:String): Observable<List<ReportSaleInvoiceVO>> {
        return database.saleReportDao().getAllSaleInvoiceReport(id)
    }
}
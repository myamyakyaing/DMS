package com.tat.dms.repositories

import com.tat.dms.vos.ReportInvoiceVO
import io.reactivex.Observable

interface RepotrRepository {
    fun allInvoiceReport(): Observable<List<ReportInvoiceVO>>
}
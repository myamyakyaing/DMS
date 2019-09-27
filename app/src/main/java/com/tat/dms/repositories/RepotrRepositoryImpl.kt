package com.tat.dms.repositories
import com.tat.dms.db.MyDatabase
import com.tat.dms.network.ApiService
import com.tat.dms.vos.ReportInvoiceVO
import io.reactivex.Observable

class RepotrRepositoryImpl(
    private val database: MyDatabase
) : RepotrRepository {
    override fun allInvoiceReport(): Observable<List<ReportInvoiceVO>> {
        return database.reportDao().getAllInvoiceReport()
    }
}
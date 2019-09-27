package com.tat.dms.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.tat.dms.vos.ReportInvoiceVO
import io.reactivex.Observable

@Dao
interface ReportDao {
    @Query("select invoice.id,customername,date,netAmount,discountPercent,discountAmount from invoice inner join customer on customer.id == invoice.customerId")
    fun getAllInvoiceReport(): Observable<List<ReportInvoiceVO>>

}
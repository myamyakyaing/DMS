package com.tat.dms.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.tat.dms.vos.ReportSaleInvoiceVO
import io.reactivex.Observable

@Dao
interface SaleReportDao {
    @Query("select productName,um,qty,price,promotionPrice,amount from invoice_item inner join product where product.id = invoice_item.product_Id")
    fun getAllSaleInvoiceReport(id:String): Observable<List<ReportSaleInvoiceVO>>
}
package com.tat.dms.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tat.dms.vos.InvoiceVO
import com.tat.dms.vos.SaleInvoiceVO
import io.reactivex.Observable

@Dao
interface CheckoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInvoice(invoice: InvoiceVO)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemsAll(list:MutableList<SaleInvoiceVO>)
}
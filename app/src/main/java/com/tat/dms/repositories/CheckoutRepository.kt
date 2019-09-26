package com.tat.dms.repositories

import com.tat.dms.vos.InvoiceVO
import com.tat.dms.vos.SaleInvoiceVO

interface CheckoutRepository {
    fun saveDataIntoDatabase(invoice: InvoiceVO, checkoutItem: MutableList<SaleInvoiceVO>)

}
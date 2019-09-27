package com.tat.dms.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoice_item")
class SaleInvoiceVO (
    @PrimaryKey()
    val id: Int,
    val invoice_id: String,
    val product_Id: String,
    val um: String,
    val qty: Int,
    val price: String,
    val promotionPrice: String,
    val amount: String
)



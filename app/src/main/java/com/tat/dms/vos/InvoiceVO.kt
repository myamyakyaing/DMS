package com.tat.dms.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkout")
class InvoiceVO (
    @PrimaryKey
    var id: String,
    var customerId: Int,
    var netAmount: String,
    var discountPercent: String,
    var discountAmt: String,
    var date:String
)

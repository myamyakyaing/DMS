package com.tat.dms.vos

data class ReportSaleInvoiceVO (
    val productName: String,
    val um: String,
    val qty: Int,
    val price: String,
    val promotionPrice: String,
    val amount: String
)

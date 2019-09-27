package com.tat.dms.vos


data class ReportInvoiceVO (
    var id: String,
    var customername: String,
    var date:String,
    var netAmount: String,
    var discountPercent: String,
    var discountAmount: String

)

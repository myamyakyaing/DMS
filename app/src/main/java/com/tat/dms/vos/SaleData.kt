package com.tat.dms.vos

import java.io.Serializable

class SaleData(
    var id:String,
    var product_name: String,
    var product_type: String,
    var product_qty: Int,
    var product_price: String,
    var foc:Boolean,
    var discount: Double
):Serializable
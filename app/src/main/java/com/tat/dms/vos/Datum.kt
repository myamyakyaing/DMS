package com.tat.dms.vos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Datum {

    @SerializedName("Product")
    @Expose
    var product: List<Product>? = null

}
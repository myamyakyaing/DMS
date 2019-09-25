package com.tat.dms.vos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data(
    @SerializedName("Customer")
    @Expose
    var customer:List<Customer> = ArrayList()
)
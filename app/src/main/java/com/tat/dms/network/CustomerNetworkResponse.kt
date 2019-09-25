package com.tat.dms.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tat.dms.vos.Data

class CustomerNetworkResponse {
    @SerializedName("aceplusStatusCode")
    @Expose
    var aceplusStatusCode:Int = 0
    @SerializedName("aceplusStatusMessage")
    @Expose
    var aceplusStatusMessage:String = ""
    @SerializedName("user_id")
    @Expose
    var userId:String = ""
    @SerializedName("data")
    @Expose
    var data:List<Data>  = ArrayList()
}



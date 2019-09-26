package com.tat.dms.network.network_response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.tat.dms.vos.Data

class CustomerNetworkResponse {
    @SerializedName("aceplusStatusCode")
    @Expose
    var aceplusStatusCode:Int? = null
    @SerializedName("aceplusStatusMessage")
    @Expose
    var aceplusStatusMessage:String? = null
    @SerializedName("user_id")
    @Expose
    var userId:String? = null
    @SerializedName("data")
    @Expose
    var data:List<Data>  = ArrayList()
}



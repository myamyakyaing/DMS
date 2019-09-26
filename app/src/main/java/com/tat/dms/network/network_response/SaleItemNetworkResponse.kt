package com.tat.dms.network.network_response

import com.tat.dms.vos.Datum
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class SaleItemNetworkResponse {

    @SerializedName("aceplusStatusCode")
    @Expose
    var aceplusStatusCode: Int? = null
    @SerializedName("aceplusStatusMessage")
    @Expose
    var aceplusStatusMessage: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: String? = null
    @SerializedName("data")
    @Expose
    var data: List<Datum>? = ArrayList()

}
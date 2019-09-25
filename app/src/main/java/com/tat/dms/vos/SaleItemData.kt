package com.tat.dms.vos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SaleItemData {

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
    var data: List<Datum>? = null


}
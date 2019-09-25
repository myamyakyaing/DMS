package com.tat.dms.vos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SendSaleItem (

    @SerializedName("site_activation_key")
    @Expose
    private  val siteActivationKey:String? = null,
    @SerializedName("tablet_activation_key")
    @Expose
    private val tabletActivationKey:String? = null,
    @SerializedName("user_id")
    @Expose
    private val userId:String? = null,
    @SerializedName("password")
    @Expose
    private val password:String? = null,
    @SerializedName("route")
    @Expose
    private val  route:Int? = null,
    @SerializedName("date")
    @Expose
    private val date:String?= null,
    @SerializedName("data")
    @Expose
    private val data :String? = null,
    @SerializedName("status")
    @Expose
    private val  status :String? = null

)
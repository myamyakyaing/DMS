package com.tat.dms.vos

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "customer")
class Customer:Serializable {
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("township_number")
    @Expose
    var townshipNumber: Int? = null
    @SerializedName("district_id")
    @Expose
    var districtId: Int? = null
    @SerializedName("shop_type_id")
    @Expose
    var shopTypeId: Int? = null
    @SerializedName("contact_person")
    @Expose
    var contactPerson: String? = null
    @SerializedName("CUSTOMER_ID")
    @Expose
    var customerid: String? = null
    @SerializedName("CUSTOMER_NAME")
    @Expose
    var customername: String? = null
    @SerializedName("CUSTOMER_TYPE_ID")
    @Expose
    var customertypeid: Int? = null
    @SerializedName("CUSTOMER_TYPE_NAME")
    @Expose
    var customertypename: String? = null
    @SerializedName("ADDRESS")
    @Expose
    var address: String? = null
    @SerializedName("PH")
    @Expose
    var ph: String? = null
    @SerializedName("CREDIT_TERM")
    @Expose
    var creditterm: Int? = null
    @SerializedName("CREDIT_LIMIT")
    @Expose
    var creditlimit: Int? = null
    @SerializedName("PAYMENT_TYPE")
    @Expose
    var paymenttype: String? = null
    @SerializedName("state_division_id")
    @Expose
    var stateDivisionId: Int? = null
    @SerializedName("LATITUDE")
    @Expose
    var latitude: Double? = null
    @SerializedName("LONGITUDE")
    @Expose
    var longitude: Double? = null
    @SerializedName("Fax")
    @Expose
    var fax: String? = null
    @SerializedName("StreetId")
    @Expose
    var streetId: Int? = null
    @SerializedName("SaleStatus")
    @Expose
    var saleStatus: String? = null
    @SerializedName("customer_category_no")
    @Expose
    var customerCategoryNo: String? = null
    @SerializedName("CREDIT_AMT")
    @Expose
    var creditamt: String? = null
    @SerializedName("DUE_AMT")
    @Expose
    var dueamt: String? = null
    @SerializedName("PREPAID_AMT")
    @Expose
    var prepaidamt: String? = null
    @SerializedName("IS_IN_ROUTE")
    @Expose
    var isinroute: String? = null
    @SerializedName("VISIT_RECORD")
    @Expose
    var visitrecord: String? = null
    @SerializedName("route_schedule_status")
    @Expose
    var routeScheduleStatus: Int? = null

}
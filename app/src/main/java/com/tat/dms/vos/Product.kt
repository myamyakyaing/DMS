package com.tat.dms.vos

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "product")
class Product : Serializable {
    @NonNull
    @PrimaryKey
    @SerializedName("Id")
    @Expose
    var id: String? = null
    @SerializedName("Product_id")
    @Expose
    var productId: String? = null
    @SerializedName("Product_name")
    @Expose
    var productName: String? = null
    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null
    @SerializedName("group_id")
    @Expose
    var groupId: String? = null
    @SerializedName("class_id")
    @Expose
    var classId: String? = null
    @SerializedName("um_id")
    @Expose
    var umId: String? = null
    @SerializedName("product_type_id")
    @Expose
    var productTypeId: String? = null
    @SerializedName("selling_price")
    @Expose
    var sellingPrice: String? = null
    @SerializedName("purchase_price")
    @Expose
    var purchasePrice: String? = null
    @SerializedName("total_qty")
    @Expose
    var totalQty: Int? = null
    @SerializedName("remaining_qty")
    @Expose
    var remainingQty: Int? = null
    @SerializedName("device_issue_status")
    @Expose
    var deviceIssueStatus: Int? = null

}
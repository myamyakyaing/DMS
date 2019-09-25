package com.tat.dms.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.tat.dms.repositories.CheckoutRepository
import com.tat.dms.vos.SaleData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class CheckoutViewModel(
    private val checkoutRepository: CheckoutRepository,
    private val context: Context
) : BaseViewModel() {
    var discountPercent = MutableLiveData<Double>()
    var discountAmount = MutableLiveData<Int>()
    var netAmount = MutableLiveData<Int>()
    var refund = MutableLiveData<Int>()
    var tax = MutableLiveData<Int>()
    var totalAmount = 0

    fun calculateTotalAmount(checkoutList: MutableList<SaleData>): Int {
        for (i in checkoutList) {
            totalAmount += i.product_qty * ((i.product_price.toDouble().roundToInt() - ((i.product_price.toDouble().roundToInt() * i.discount) / 100)).roundToInt())
        }
        return totalAmount
    }

    fun calculateDiscountAmount(discount: Double) {
        discountAmount.postValue((totalAmount * discount / 100).roundToInt())
    }

    fun calculateDiscountPercent(discount: Int) {
        discountPercent.postValue((discount * 100 / totalAmount).toDouble())
    }

    fun calculateNetAmount(discount: String) {
        if (!discount.isNullOrEmpty()) {
            netAmount.postValue(totalAmount - discount.toInt())
        } else {
            netAmount.postValue(totalAmount - 0)
        }
    }

    fun calculateRefund(payAmount: Int, netAmount: Int) {
        if (payAmount < netAmount) {
            Toast.makeText(context, "Payment amount is not enough", Toast.LENGTH_LONG).show()
        } else {
            refund.postValue(payAmount - netAmount)
        }
    }

    fun calculateTax(netAmount: Int) {
        tax.postValue((netAmount * 5 / 100))
    }


    fun setCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(currentDate)
    }
}
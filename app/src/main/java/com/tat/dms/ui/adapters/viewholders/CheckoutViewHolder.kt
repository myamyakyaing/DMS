package com.tat.dms.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.vos.SaleData
import kotlinx.android.synthetic.main.product_item_vouncher.view.*
import kotlin.math.roundToInt

class CheckoutViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setData(saleData: SaleData) {
        view.apply {
            txt_report_invoice_id.text = saleData.product_name
            txt_report_customer_name.text = saleData.product_type
            txt_report_date.text = saleData.product_qty.toString()

            val salePrice = saleData.product_price.toDouble().roundToInt()
            txt_report_net_amount.text = salePrice.toString()

            val promoPrice = (salePrice - ((salePrice * saleData.discount) / 100)).roundToInt()
            txt_report_discount_per.text = promoPrice.toString()

            val amount = promoPrice * saleData.product_qty
            txt_report_discount_amt.text = amount.toString()

        }
    }
}
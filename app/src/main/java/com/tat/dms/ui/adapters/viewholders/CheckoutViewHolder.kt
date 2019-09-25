package com.tat.dms.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.vos.SaleData
import kotlinx.android.synthetic.main.product_item_vouncher.view.*
import kotlin.math.roundToInt

class CheckoutViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun setData(saleData: SaleData) {
        view.apply {
            txt_product_name.text = saleData.product_name
            txt_product_type.text = saleData.product_type
            txt_product_qty.text = saleData.product_qty.toString()

            val salePrice = saleData.product_price.toDouble().roundToInt()
            txt_product_price.text = salePrice.toString()

            val promoPrice = (salePrice - ((salePrice * saleData.discount) / 100)).roundToInt()
            txt_product_pro.text = promoPrice.toString()

            val amount = promoPrice * saleData.product_qty
            txt_product_amount.text = amount.toString()

        }
    }
}
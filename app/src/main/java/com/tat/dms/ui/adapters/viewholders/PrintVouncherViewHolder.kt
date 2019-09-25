package com.tat.dms.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.vos.SaleData
import kotlinx.android.synthetic.main.print_item_vouncher.view.*
import kotlin.math.roundToInt

class PrintVouncherViewHolder(private val view:View):RecyclerView.ViewHolder(view) {
    fun setData(saleData: SaleData) {
        view.apply {
            txt_print_item_names.text = saleData.product_name
            txt_print_items_qty.text = saleData.product_qty.toString()

            val salePrice = saleData.product_price.toDouble().roundToInt()
            view.txt_print_item_prices.text = salePrice.toString()
            val promoPrice = (salePrice - ((salePrice * saleData.discount) / 100)).roundToInt()
            val amount = promoPrice * saleData.product_qty
            view.txt_print_item_amounts.text = amount.toString()

        }
    }
}
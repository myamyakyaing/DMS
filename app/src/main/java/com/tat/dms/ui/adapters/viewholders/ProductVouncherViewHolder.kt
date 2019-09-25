package com.tat.dms.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.vos.SaleData
import kotlinx.android.synthetic.main.sales_item_vouncher.view.*
import kotlin.math.roundToInt

class ProductVouncherViewHolder(
    private val view: View,
    private val onClickQty: (position: Int, currentQty: Int) -> Unit,
    private val onClickFoc: (position: Int, currentFoc: Boolean) -> Unit,
    private val onclickDisc: (position: Int, currentDisc: Double) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun setData(saleData: SaleData, position: Int) {
        view.apply {
            view.txt_item_name.text = saleData.product_name
            view.txt_item_type.text = saleData.product_type

            view.btn_item_qty.text = saleData.product_qty.toString()
            view.btn_item_qty.setOnClickListener { onClickQty(position, saleData.product_qty) }

            val salePrice = saleData.product_price.toFloat().roundToInt()
            view.txt_item_price.text = salePrice.toString()

            val promoPrice = (salePrice - ((salePrice * saleData.discount) / 100)).roundToInt()
            view.txt_item_pro.text = promoPrice.toString()

            val amount = promoPrice * saleData.product_qty
            view.txt_item_amount.text = amount.toString()

            view.cb_foc.isSelected = saleData.foc
            view.cb_foc.setOnCheckedChangeListener { cb, checked ->
                onClickFoc(position, checked)
            }

            view.btn_item_discount.text = saleData.discount.toString()
            view.btn_item_discount.setOnClickListener { onclickDisc(position, saleData.discount) }
        }

    }
}

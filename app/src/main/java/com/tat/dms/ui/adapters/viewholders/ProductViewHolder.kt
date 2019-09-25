package com.tat.dms.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.vos.Product
import kotlinx.android.synthetic.main.customer_list_item.view.*

class ProductViewHolder(
    private val view: View,
    private val onClick: (product:Product) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun setData(data: Product) {
        view.apply {
            customer_response_name.text = data.productName
            setOnClickListener { onClick(data) }
        }
    }
}

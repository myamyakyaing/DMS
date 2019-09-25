package com.tat.dms.ui.adapters.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.vos.Customer
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.customer_list_item.view.*

class CustomerViewHolder(
    private val view: View,
    private val onClick: (customer: Customer) -> Unit
) : RecyclerView.ViewHolder(view) {
    fun setData(data: Customer) {
        view.apply {
            customer_response_name.text = data.customername
            setOnClickListener { onClick(data) }
        }
    }
}
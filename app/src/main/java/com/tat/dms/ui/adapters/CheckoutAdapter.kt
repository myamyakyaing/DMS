package com.tat.dms.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tat.dms.R
import com.tat.dms.ui.adapters.viewholders.CheckoutViewHolder
import com.tat.dms.vos.SaleData

class CheckoutAdapter(private val list: MutableList<SaleData>) :
    RecyclerView.Adapter<CheckoutViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_vouncher, parent, false)
        return CheckoutViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.setData(list[position])
    }
}